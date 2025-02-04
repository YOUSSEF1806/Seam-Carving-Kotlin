import com.youyou.backTrackGraph
import com.youyou.dijkstraEnergyPath
import com.youyou.generateGraphFindMinSeam
import com.youyou.model.Pixel
import kotlin.test.BeforeTest
import kotlin.test.Test

class TestSeamProcessing {
    private lateinit var fakeEnergies: List<List<Double>>

    @BeforeTest
    fun setUpData() {
        fakeEnergies = listOf(
            listOf(12.5, 20.5, 1.2, 6.5, 70.9, 2.4),
            listOf(44.6, 15.2, 98.1, 7.3, 3.2, 12.0),
            listOf(28.5, 12.5, 125.2, 185.5, 70.9, 8.4),
            listOf(94.6, 1.2, 8.1, 27.3, 30.2, 185.0),
            listOf(0.6, 18.2, 81.1, 207.3, 37.2, 15.3)
        )
    }

    @Test
    fun testDijkstraEnergyPath() {
        println("*** Input sample ***")
        fakeEnergies.forEach { println(it) }
        println("*** Path found ***")
        val paths = fakeEnergies.first().indices.map { Pixel(0, it) }
            .map { backTrackGraph(dijkstraEnergyPath(fakeEnergies, it), fakeEnergies.lastIndex) }
//        graph.forEach(::println)
//        val path = backTrackGraph(graph, fakeEnergies.lastIndex)
        paths.forEach(::println)
    }

    @Test
    fun testSeamProcessingV2() {
        val (minSeamEnergy, seamPath) = generateGraphFindMinSeam(fakeEnergies)
        println("*** Input sample ***")
        fakeEnergies.forEach { println(it) }
        println("*** Path found ***")
        println("energy: $minSeamEnergy")
        seamPath.forEach(::println)
    }
}