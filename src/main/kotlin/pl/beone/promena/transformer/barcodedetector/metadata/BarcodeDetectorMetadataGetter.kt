package pl.beone.promena.transformer.barcodedetector.metadata

import pl.beone.promena.transformer.barcodedetector.metadata.BarcodeDetectorMetadataConstants.Barcode.CONTOUR_VERTICES_ON_PAGE
import pl.beone.promena.transformer.barcodedetector.metadata.BarcodeDetectorMetadataConstants.Barcode.FORMAT
import pl.beone.promena.transformer.barcodedetector.metadata.BarcodeDetectorMetadataConstants.Barcode.PAGE
import pl.beone.promena.transformer.barcodedetector.metadata.BarcodeDetectorMetadataConstants.Barcode.TEXT
import pl.beone.promena.transformer.barcodedetector.metadata.BarcodeDetectorMetadataConstants.Barcode.Vertex.X
import pl.beone.promena.transformer.barcodedetector.metadata.BarcodeDetectorMetadataConstants.Barcode.Vertex.Y
import pl.beone.promena.transformer.barcodedetector.metadata.BarcodeDetectorMetadataConstants.ROOT
import pl.beone.promena.transformer.contract.model.Metadata

data class BarcodeDetectorMetadataGetter(
    val metadata: Metadata
) {

    data class Barcode(
        val metadata: Metadata
    ) {

        data class Vertex(
            val metadata: Metadata
        ) {

            fun getX(): Int =
                metadata.get(X, Int::class.java)

            fun getY(): Int =
                metadata.get(Y, Int::class.java)
        }

        fun getText(): String =
            metadata.get(TEXT, String::class.java)

        fun getFormat(): String =
            metadata.get(FORMAT, String::class.java)

        fun getPage(): Int =
            metadata.get(PAGE, Int::class.java)

        fun getContourVerticesOnPage(): List<Vertex> =
            metadata.getList(CONTOUR_VERTICES_ON_PAGE, Metadata::class.java).map(::Vertex)
    }

    fun getBarcodes(): List<Barcode> =
        metadata.getList(ROOT, Metadata::class.java).map(::Barcode)
}