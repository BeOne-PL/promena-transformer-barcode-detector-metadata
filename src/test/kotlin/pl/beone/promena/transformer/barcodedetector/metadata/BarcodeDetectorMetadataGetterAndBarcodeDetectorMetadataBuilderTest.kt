package pl.beone.promena.transformer.barcodedetector.metadata

import io.kotlintest.matchers.collections.shouldHaveSize
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrowExactly
import org.junit.jupiter.api.Test
import pl.beone.promena.transformer.barcodedetector.metadata.BarcodeDetectorMetadataBuilder.BarcodeBuilder
import pl.beone.promena.transformer.barcodedetector.metadata.BarcodeDetectorMetadataBuilder.BarcodeBuilder.VertexBuilder
import pl.beone.promena.transformer.barcodedetector.metadata.BarcodeDetectorMetadataConstants.Barcode.TEXT
import pl.beone.promena.transformer.barcodedetector.metadata.BarcodeDetectorMetadataConstants.Barcode.Vertex.X
import pl.beone.promena.transformer.barcodedetector.metadata.BarcodeDetectorMetadataConstants.Barcode.Vertex.Y

class BarcodeDetectorMetadataGetterAndBarcodeDetectorMetadataBuilderTest {

    @Test
    fun `build _ general test`() {
        val barcodeText = "0123456789"
        val barcodeFormat = "QR Code"
        val barcodePage = 1
        val barcodeVertexX = 1
        val barcodeVertexY = 2
        val barcodeVertex2X = 3
        val barcodeVertex2Y = 4

        with(
            BarcodeDetectorMetadataGetter(
                BarcodeDetectorMetadataBuilder()
                    .barcode(
                        BarcodeBuilder()
                            .text(barcodeText)
                            .format(barcodeFormat)
                            .page(barcodePage)
                            .contourVerticesOnPage(VertexBuilder().x(barcodeVertexX).y(barcodeVertexY).build())
                            .contourVerticesOnPage(VertexBuilder().x(barcodeVertex2X).y(barcodeVertex2Y).build())
                            .build()
                    )
                    .barcode(
                        BarcodeBuilder()
                            .text(barcodeText)
                            .build()
                    )
                    .build()
            )
        ) {
            with(getBarcodes()) {
                this shouldHaveSize 2

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
                    getText() shouldBe barcodeText
                    shouldThrowExactly<NoSuchElementException> { getFormat() }
                    shouldThrowExactly<NoSuchElementException> { getPage() }
                    shouldThrowExactly<NoSuchElementException> { getContourVerticesOnPage() }
                }
            }

        }
    }

    @Test
    fun `build _ no barcodes _ should throw NoSuchElementException`() {
        shouldThrowExactly<NoSuchElementException> {
            BarcodeDetectorMetadataGetter(
                BarcodeDetectorMetadataBuilder().build()
            ).getBarcodes()
        }
    }

    @Test
    fun `builder _ barcode text not set _ should throw IllegalArgumentException`() {
        shouldThrowExactly<IllegalArgumentException> {
            BarcodeDetectorMetadataBuilder()
                .barcode(
                    BarcodeBuilder()
                        .format("no matter")
                        .page(1)
                        .contourVerticesOnPage(VertexBuilder().x(0).y(0).build())
                        .build()
                )
                .build()
        }.message shouldBe "Parameter <$TEXT> must be set"
    }

    @Test
    fun `builder _ barcode vertex coordinate not set _ should throw IllegalArgumentException`() {
        shouldThrowExactly<IllegalArgumentException> {
            BarcodeDetectorMetadataBuilder()
                .barcode(
                    BarcodeBuilder()
                        .text("no matter")
                        .contourVerticesOnPage(VertexBuilder().y(0).build())
                        .build()
                )
                .build()
        }.message shouldBe "Parameter <$X> must be set"

        shouldThrowExactly<IllegalArgumentException> {
            BarcodeDetectorMetadataBuilder()
                .barcode(
                    BarcodeBuilder()
                        .text("no matter")
                        .contourVerticesOnPage(VertexBuilder().x(0).build())
                        .build()
                )
                .build()
        }.message shouldBe "Parameter <$Y> must be set"
    }
}