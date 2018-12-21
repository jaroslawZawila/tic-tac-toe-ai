package zawila.tic.tac.toe.ai

import java.nio.file.Paths

import org.platanios.tensorflow.api.config.TensorBoardConfig
import org.platanios.tensorflow.api.core.Shape
import org.platanios.tensorflow.api.learn.{Configuration, Model, StopCriteria}
import org.platanios.tensorflow.api.learn.estimators.InMemoryEstimator
import org.platanios.tensorflow.api.learn.hooks.{CheckpointSaver, StepHookTrigger, SummarySaver}
import org.platanios.tensorflow.api.learn.layers._
import org.platanios.tensorflow.api.ops.Output
import org.platanios.tensorflow.api.ops.data.Dataset
import org.platanios.tensorflow.api.tensors.Tensor
import org.platanios.tensorflow.api.{FLOAT32, INT64, tf}
import org.platanios.tensorflow.data.image.{MNISTDataset, MNISTLoader}

import scala.io.Source

object Main extends App {

  val lines = Source.fromResource("endOfGame.csv").getLines.toList


  lines.map(_.split(",")).take(2).map {x =>
    x.toList.map{ x => x match {
      case "x" => 0
      case "o" => 1
      case "b" => 2
      case "positive" => 10
      case "negative" => -1
    }
    }
  }.foreach(println)

  println(lines.head)

  println(lines.size)

//  val dataset: MNISTDataset = MNISTLoader.load(Paths.get("datasets/MNIST"))
//  val trainImages: Dataset[Output[Float]] = tf.data.datasetFromTensorSlices(dataset.trainImages.toFloat)
//  val trainLabels: Dataset[Output[Long]] = tf.data.datasetFromTensorSlices(dataset.trainLabels.toLong)
//  val trainData =
//    trainImages.zip(trainLabels)
//      .repeat()
//      .shuffle(10000)
//      .batch(256)
//      .prefetch(10)

  val gameStatus = Tensor(Tensor(1,2,3,4,5,6,8,9,1,2),Tensor(1,2,3,4,5,6,8,9,1,2))
  val gameResult = Tensor(Tensor(1.04), Tensor(1.3))
//
//
//  val td1: Dataset[Output[Float]] = tf.data.datasetFromTensorSlices(gameStatus.toFloat)
  val td2: Dataset[Output[Long]] = tf.data.datasetFromTensorSlices(gameStatus.toLong)
//
//  val trainData: Dataset[(Output[Float], Output[Long])] = td1.zip(td2)
//    .repeat()
//    .shuffle(223)


  val tt1 = Tensor(Tensor(1,2,3,4,5,6,8,9,1,2),Tensor(1,2,3,4,5,6,8,9,1,2),Tensor(1,2,3,4,5,6,8,9,1,2),Tensor(1,2,3,4,5,6,8,9,1,2),Tensor(1,2,3,4,5,6,8,9,1,2),Tensor(1,2,3,4,5,6,8,9,1,2),Tensor(1,2,3,4,5,6,8,9,1,2),Tensor(1,2,3,4,5,6,8,9,1,2),Tensor(1,2,3,4,5,6,8,9,1,2),Tensor(1,2,3,4,5,6,8,9,1,2))
//  val tt2 = Tensor(Tensor(2),Tensor(2))

  val s = tt1.reshape(Shape(10,10))
  println(s.shape)

  val tt1d = tf.data.datasetFromTensorSlices(s.toFloat).zip(td2)

  tt1d.outputDataTypes.


  val input = Input(FLOAT32, Shape(10))
  val trainInput = Input(INT64, Shape(-1))
  val layer =
    Flatten[Float]("Input/Flatten") >>
    Linear[Float]("Layer_0", 10) >> ReLU[Float]("Layer_0/Activation", 0.1f) >>
    Linear[Float]("Layer_1", 5) >> ReLU[Float]("Layer_1/Activation", 0.1f) >>
    Linear[Float]("Layer_2", 2) >> ReLU[Float]("Layer_2/Activation", 0.1f) >>
    Linear[Float]("OutputLayer", 10)

  val loss = SparseSoftmaxCrossEntropy[Float, Long, Float]("Loss") >>
    Mean("Loss/Mean") >>
    ScalarSummary(name = "Loss", tag = "Loss")

  val optimizer = tf.train.GradientDescent(1e-6f)

  val model = Model.simpleSupervised(input, trainInput, layer, loss, optimizer)

  val summariesDir = Paths.get("tmp/summaries")
  val estimator = InMemoryEstimator(
    modelFunction = model,
    configurationBase = Configuration(Some(summariesDir)),
    trainHooks = Set(
      SummarySaver(summariesDir, StepHookTrigger(100)),
      CheckpointSaver(summariesDir, StepHookTrigger(1000))),
    tensorBoardConfig = TensorBoardConfig(summariesDir))

  estimator.train(() => tt1d, StopCriteria(maxSteps = Some(100)))

}
