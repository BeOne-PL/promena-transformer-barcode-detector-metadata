package pl.beone.promena.transformer.barcodedetector.metadata

import pl.beone.promena.transformer.barcodedetector.metadata.BarcodeDetectorMetadataConstants.Barcode.CONTOUR_VERTICES_ON_PAGE
import pl.beone.promena.transformer.barcodedetector.metadata.BarcodeDetectorMetadataConstants.Barcode.FORMAT
import pl.beone.promena.transformer.barcodedetector.metadata.BarcodeDetectorMetadataConstants.Barcode.PAGE
import pl.beone.promena.transformer.barcodedetector.metadata.BarcodeDetectorMetadataConstants.Barcode.TEXT
import pl.beone.promena.transformer.barcodedetector.metadata.BarcodeDetectorMetadataConstants.Barcode.Vertex.X
import pl.beone.promena.transformer.barcodedetector.metadata.BarcodeDetectorMetadataConstants.Barcode.Vertex.Y
import pl.beone.promena.transformer.barcodedetector.metadata.BarcodeDetectorMetadataConstants.ROOT
import pl.beone.promena.transformer.contract.model.Metadata
import pl.beone.promena.transformer.internal.model.metadata.addIfNotNull
import pl.beone.promena.transformer.internal.model.metadata.emptyMetadata

class BarcodeDetectorMetadataBuilder() {

    private var barcodesMetadata: List<Metadata>? = null

    fun barcode(barcodeMetadata: Metadata): BarcodeDetectorMetadataBuilder =
        apply { barcodesMetadata = (barcodesMetadata ?: emptyList()) + barcodeMetadata }

    fun build(): Metadata =
        emptyMetadata() addIfNotNull (ROOT to barcodesMetadata)

    class BarcodeBuilder() {

        private var text: String? = null
        private var format: String? = null
        private var page: Int? = null
        private var contourVerticesOnPagesMetadata: List<Metadata>? = null

        fun text(text: String): BarcodeBuilder =
            apply { this.text = text }

        fun format(format: String): BarcodeBuilder =
            apply { this.format = format }

        fun page(page: Int): BarcodeBuilder =
            apply { this.page = page }

        fun contourVerticesOnPage(vertexMetadata: Metadata): BarcodeBuilder =
            apply { contourVerticesOnPagesMetadata = (contourVerticesOnPagesMetadata ?: emptyList()) + vertexMetadata }

        fun build(): Metadata {
            require(text != null) { "Parameter <$TEXT> must be set" }

            return emptyMetadata() addIfNotNull
                    (TEXT to text) addIfNotNull
                    (FORMAT to format) addIfNotNull
                    (PAGE to page) addIfNotNull
                    (CONTOUR_VERTICES_ON_PAGE to contourVerticesOnPagesMetadata)
        }

        class VertexBuilder() {

            private var x: Int? = null
            private var y: Int? = null

            fun x(x: Int): VertexBuilder =
                apply { this.x = x }

            fun y(y: Int): VertexBuilder =
                apply { this.y = y }

            fun build(): Metadata {
                require(x != null) { "Parameter <$X> must be set" }
                require(y != null) { "Parameter <$Y> must be set" }

                return emptyMetadata() addIfNotNull
                        (X to x) addIfNotNull
                        (Y to y)
            }
        }
    }
}