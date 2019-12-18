package pl.beone.promena.transformer.barcodedetector.metadata

object BarcodeDetectorMetadataConstants {
    const val ROOT = "barcode detector"

    object Barcode {
        const val TEXT = "text"
        const val FORMAT = "format"
        const val PAGE = "page"
        const val CONTOUR_VERTICES_ON_PAGE = "contourVerticesOnPage"

        object Vertex {
            const val X = "x"
            const val Y = "y"
        }
    }
}