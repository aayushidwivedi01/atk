/*
// Copyright (c) 2015 Intel Corporation 
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
*/
package org.trustedanalytics.atk.engine.model.plugins.dimensionalityreduction

import org.apache.spark.mllib.atk.plugins.MLLibJsonProtocol
import org.trustedanalytics.atk.domain.CreateEntityArgs
import org.trustedanalytics.atk.domain.model.{ GenericNewModelArgs, ModelReference }
import org.trustedanalytics.atk.engine.plugin.{ Invocation, PluginDoc }
import org.trustedanalytics.atk.engine.plugin.SparkCommandPlugin
import org.trustedanalytics.atk.domain.DomainJsonProtocol._
import MLLibJsonProtocol._

@PluginDoc(oneLine = "Create a 'new' instance of principal components model.",
  extended = """
*Dimensionality Reduction using Principal Component Analysis.*

Principal component analysis[1]_ is a statistical algorithm that converts possibly correlated features to linearly uncorrelated
variables called principal components. The number of principal components is less than or equal to the number of original
variables. This implementation of computing Principal Components is done by Singular Value Decomposition[2]_ of the data,
providing the user with an option to mean center the data. The user may initialize a PrincipalComponentsModel, train the model
specifying the observation columns of the frame, the number of components, and predict principal components using the trained model.
MLLib's Singular Value Decomposition[3]_ has been used for this, with additional features to mean center the data during train and
predict and compute the t-squared index during prediction.

.. [1] https://en.wikipedia.org/wiki/Principal_component_analysis
.. [2] https://en.wikipedia.org/wiki/Singular_value_decomposition
.. [3] https://spark.apache.org/docs/1.3.0/mllib-dimensionality-reduction.html
             """)
class PrincipalComponentsNewPlugin extends SparkCommandPlugin[GenericNewModelArgs, ModelReference] {
  /**
   * The name of the command.
   *
   * The format of the name determines how the plugin gets "installed" in the client layer
   * e.g Python client via code generation.
   */
  override def name: String = "model:principal_components/new"

  override def execute(arguments: GenericNewModelArgs)(implicit invocation: Invocation): ModelReference = {
    engine.models.createModel(CreateEntityArgs(name = arguments.name, entityType = Some("model:principal_components")))
  }
}

