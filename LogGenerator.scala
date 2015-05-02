import java.io.File
import java.io.PrintWriter
import java.sql.Timestamp

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

object LogGenerator extends App {

  println("Generating the Log file!")
  var i = 0;
  var t, d, s = "";
  val numRecords = 2000;
  val list = getTime().sorted;
  val writer = new PrintWriter(new File("doors_log.txt"))

  while (i < numRecords) {
    t = list(i).toString();
    d = getInt(200);
    s = getState().toString();
    writer.print(t + " " + "Door_" + d + " " + s + "\n");
    i += 1;
  }
  writer.close();

  object State extends Enumeration {
    type State = Value;
    val IN, OUT = Value
  }

  // import State._
  def getState(): String = {
    State(Random.nextInt(State.values.size)).toString()
  }

  def getInt(max: Int): String = {
    Random.nextInt(max).toString()
  }

  def getTime(): ArrayBuffer[Timestamp] = {
    val offset = Timestamp.valueOf("2015-01-01 00:00:00").getTime();
    val end = Timestamp.valueOf("2015-01-02 00:00:00").getTime();
    val diff = end - offset + 1;

    val mList = ArrayBuffer[Timestamp]();

    var i = 0;
    while (i < numRecords) {
      val rand = new Timestamp(offset + (Math.random() * diff).toLong);

      mList += rand;
      i += 1;
    }
    mList;

  }

  implicit def ordered: Ordering[Timestamp] = new Ordering[Timestamp] {
    def compare(x: Timestamp, y: Timestamp): Int = x compareTo y
  }

}
