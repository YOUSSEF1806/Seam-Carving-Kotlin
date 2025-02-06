import com.youyou.createGraphNodes
import com.youyou.getMinSeam
import kotlin.test.BeforeTest
import kotlin.test.Test

class TestSeamProcessing {
    private lateinit var fakeEnergies: List<List<Double>>
    private lateinit var bigFakeEnergies: List<List<Double>>

    @BeforeTest
    fun setUpData() {
        fakeEnergies = listOf(
            listOf(12.5, 20.5, 1.2, 6.5, 70.9, 2.4),
            listOf(44.6, 15.2, 98.1, 7.3, 3.2, 12.0),
            listOf(28.5, 12.5, 125.2, 185.5, 70.9, 8.4),
            listOf(94.6, 1.2, 8.1, 27.3, 30.2, 185.0),
            listOf(0.6, 18.2, 81.1, 207.3, 37.2, 15.3)
        )
        bigFakeEnergies = listOf(
            listOf(126.0, 173.0, 166.0, 127.0, 163.0, 188.0, 8.0, 62.0, 100.0, 45.0, 99.0, 58.0, 26.0, 35.0, 45.0),
            listOf(142.0, 166.0, 161.0, 126.0, 146.0, 242.0, 96.0, 50.0, 94.0, 52.0, 70.0, 42.0, 26.0, 33.0, 44.0),
            listOf(101.0, 97.0, 42.0, 34.0, 59.0, 207.0, 147.0, 42.0, 72.0, 82.0, 68.0, 78.0, 55.0, 81.0, 92.0),
            listOf(61.0, 48.0, 10.0, 21.0, 76.0, 231.0, 150.0, 46.0, 12.0, 100.0, 62.0, 38.0, 136.0, 189.0, 188.0),
            listOf(71.0, 53.0, 32.0, 53.0, 250.0, 183.0, 130.0, 77.0, 24.0, 65.0, 62.0, 34.0, 133.0, 150.0, 147.0),
            listOf(80.0, 76.0, 73.0, 188.0, 255.0, 20.0, 124.0, 84.0, 37.0, 79.0, 86.0, 37.0, 178.0, 223.0, 224.0),
            listOf(86.0, 94.0, 102.0, 144.0, 182.0, 160.0, 114.0, 84.0, 65.0, 58.0, 82.0, 122.0, 135.0, 79.0, 75.0),
            listOf(76.0, 77.0, 93.0, 76.0, 187.0, 177.0, 154.0, 54.0, 25.0, 45.0, 64.0, 122.0, 137.0, 117.0, 129.0),
            listOf(86.0, 84.0, 82.0, 43.0, 38.0, 51.0, 104.0, 67.0, 34.0, 42.0, 40.0, 64.0, 56.0, 33.0, 35.0),
            listOf(85.0, 83.0, 83.0, 50.0, 33.0, 43.0, 105.0, 65.0, 32.0, 32.0, 22.0, 59.0, 71.0, 43.0, 44.0)
        )
    }
    
    @Test
    fun testCreateGraphNodes() {
        var resultGraph = createGraphNodes(fakeEnergies)
        resultGraph.forEach { println(it) }

        println("\n======================================\n")
        resultGraph = createGraphNodes(bigFakeEnergies)
        resultGraph.forEach { println(it) }
    }

    @Test
    fun testGetMinSeam() {
        val (totalEnergy, path) = getMinSeam(createGraphNodes(fakeEnergies))
        println("$totalEnergy => $path")

        println("\n======================================\n")
        val (totalEnergy2, path2) = getMinSeam(createGraphNodes(bigFakeEnergies))
        println("$totalEnergy2 => $path2")
    }
}