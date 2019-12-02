package pl.beone.promena.transformer.barcodedetector.metadata

import pl.beone.promena.transformer.barcodedetector.metadata.BarcodeDetectorConstants.TRANSFORMER_NAME
import pl.beone.promena.transformer.contract.model.Metadata

data class BarcodeDetectorMetadata(
    internal val metadata: Metadata
) : Metadata by metadata {

    data class Barcode(
        internal val metadata: Metadata
    ) : Metadata by metadata {

        companion object {
            const val TEXT = "text"
            const val FORMAT = "format"
            const val PAGE = "page"
            const val CONTOUR_VERTICES_ON_PAGE = "contourVerticesOnPage"
        }

        data class Vertex(
            internal val metadata: Metadata
        ) : Metadata by metadata {

            companion object {
                const val X = "x"
                const val Y = "y"
            }

            fun getX(): Int =
                get(X, Int::class.java)

            fun getY(): Int =
                get(Y, Int::class.java)
        }

        fun getText(): String =
            get(TEXT, String::class.java)

        fun getFormat(): String =
            get(FORMAT, String::class.java)

        fun getPage(): Int =
            get(PAGE, Int::class.java)

        fun getContourVerticesOnPage(): List<Vertex> =
            getListOrDefault(CONTOUR_VERTICES_ON_PAGE, Vertex::class.java, emptyList())
    }

    fun getBarcodes(): List<Barcode> =
        getList(TRANSFORMER_NAME, Barcode::class.java)
}