package pl.beone.promena.transformer.barcodedetector.metadata

import io.kotlintest.matchers.collections.shouldHaveSize
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrowExactly
import org.junit.jupiter.api.Test

class BarcodeDetectorMetadataDslTest {

    companion object {
        private const val barcodeText = "0123456789"
        private const val barcodeFormat = "QR Code"
        private const val barcodePage = 1
        private const val barcodeVertexX = 1
        private const val barcodeVertexY = 2
        private const val barcodeVertex2X = 3
        private const val barcodeVertex2Y = 4

        private const val barcode2VertexX = 10
        private const val barcode2VertexY = 11
    }

    @Test
    fun `all possible syntax`() {
        with(
            barcodeDetectorMetadata() addBarcode
                    (barcode() addText
                            barcodeText addFormat
                            barcodeFormat addPage
                            barcodePage addContourVertexOnPage
                            (barcodeVertexX to barcodeVertexY) addContourVertexOnPage
                            (barcodeVertex2X to barcodeVertex2Y)) addBarcode
                    (barcode() addContourVerticesOnPage
                            (listOf((barcodeVertexX to barcodeVertexY), (barcodeVertex2X to barcodeVertex2Y)))) addBarcode
                    barcode()
        ) {
            with(getBarcodes()) {
                this shouldHaveSize 3

                with(getBarcodes()[0]) {
                    getText() shouldBe barcodeText
                    getFormat() shouldBe barcodeFormat
                    getPage() shouldBe barcodePage
                    with(getContourVerticesOnPage()) {
                        this shouldHaveSize 2
                        with(this[0]) {
                            getX() shouldBe barcodeVertexX
                            getY() shouldBe barcodeVertexY
                        }
                        with(this[1]) {
                            getX() shouldBe barcodeVertex2X
                            getY() shouldBe barcodeVertex2Y
                        }
                    }
                }

                with(getBarcodes()[1]) {
                    shouldThrowExactly<NoSuchElementException> { getText() }
                    shouldThrowExactly<NoSuchElementException> { getFormat() }
                    shouldThrowExactly<NoSuchElementException> { getPage() }
                    with(getContourVerticesOnPage()) {
                        this shouldHaveSize 2
                        with(this[0]) {
                            getX() shouldBe barcodeVertexX
                            getY() shouldBe barcodeVertexY
                        }
                        with(this[1]) {
                            getX() shouldBe barcodeVertex2X
                            getY() shouldBe barcodeVertex2Y
                        }
                    }
                }

                with(getBarcodes()[2]) {
                    shouldThrowExactly<NoSuchElementException> { getText() }
                    shouldThrowExactly<NoSuchElementException> { getFormat() }
                    shouldThrowExactly<NoSuchElementException> { getPage() }
                    getContourVerticesOnPage() shouldHaveSize 0
                }
            }
        }
    }

    @Test
    fun `initial list of barcodes`() {
        with(
            barcodeDetectorMetadata(
                listOf(barcode() addContourVertexOnPage (barcode2VertexX to barcode2VertexY))
            ).getBarcodes()
        ) {
            this shouldHaveSize 1
            with(this[0]) {
                shouldThrowExactly<NoSuchElementException> { getText() }
                shouldThrowExactly<NoSuchElementException> { getFormat() }
                shouldThrowExactly<NoSuchElementException> { getPage() }
                with(getContourVerticesOnPage()) {
                    this shouldHaveSize 1
                    with(this[0]) {
                        getX() shouldBe barcode2VertexX
                        getY() shouldBe barcode2VertexY
                    }
                }
            }
        }
    }
}