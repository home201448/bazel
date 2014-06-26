// Copyright 2014 Google Inc. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.google.devtools.build.lib.view.config;

import com.google.devtools.build.lib.blaze.BlazeDirectories;
import com.google.devtools.build.lib.view.config.BuildConfiguration.Fragment;

import java.util.List;
import java.util.Map;

/**
 * A factory that creates configuration fragments.
 */
public interface ConfigurationFragmentFactory {
  /**
   * Creates a configuration fragment.
   *
   * @param env the ConfigurationEnvironment for querying targets and paths
   * @param buildOptions command-line options (see {@link FragmentOptions})
   * @param requiredFragments already created configuration fragments that the currently generated
   *        fragment depends on. Matches the list specified by {@code requires()}.
   *
   * @return the configuration fragment.
   */
  BuildConfiguration.Fragment create(ConfigurationEnvironment env, BlazeDirectories directories,
      BuildOptions buildOptions, Map<Class<? extends Fragment>, Fragment> requiredFragments)
      throws InvalidConfigurationException;

  /**
   * @return the exact type of the fragment this factory creates.
   */
  Class<? extends Fragment> creates();

  /**
   * @return the list of fragment classes on which this factory depends on. It's guaranteed
   *         that those fragments will be created before the create() method is called.
   */
  List<Class<? extends Fragment>> requires();
}