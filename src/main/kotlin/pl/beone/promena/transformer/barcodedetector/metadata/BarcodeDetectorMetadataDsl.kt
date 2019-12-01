@file:JvmName("BarcodeDetectorMetadataDsl")

package pl.beone.promena.transformer.barcodedetector.metadata

import pl.beone.promena.transformer.barcodedetector.metadata.BarcodeDetectorConstants.TRANSFORMER_NAME
import pl.beone.promena.transformer.barcodedetector.metadata.BarcodeDetectorMetadata.Barcode
import pl.beone.promena.transformer.barcodedetector.metadata.BarcodeDetectorMetadata.Barcode.Companion.CONTOUR_VERTICES_ON_PAGE
import pl.beone.promena.transformer.barcodedetector.metadata.BarcodeDetectorMetadata.Barcode.Companion.FORMAT
import pl.beone.promena.transformer.barcodedetector.metadata.BarcodeDetectorMetadata.Barcode.Companion.PAGE
import pl.beone.promena.transformer.barcodedetector.metadata.BarcodeDetectorMetadata.Barcode.Companion.TEXT
import pl.beone.promena.transformer.barcodedetector.metadata.BarcodeDetectorMetadata.Barcode.Vertex
import pl.beone.promena.transformer.barcodedetector.metadata.BarcodeDetectorMetadata.Barcode.Vertex.Companion.X
import pl.beone.promena.transformer.barcodedetector.metadata.BarcodeDetectorMetadata.Barcode.Vertex.Companion.Y
import pl.beone.promena.transformer.internal.model.metadata.emptyMetadata
import pl.beone.promena.transformer.internal.model.metadata.plus

fun barcodeDetectorMetadata(barcodes: List<Barcode> = emptyList()): BarcodeDetectorMetadata =
    BarcodeDetectorMetadata(
        emptyMetadata() + (TRANSFORMER_NAME to barcodes)
    )

infix fun BarcodeDetectorMetadata.addBarcode(barcode: Barcode): BarcodeDetectorMetadata =
    BarcodeDetectorMetadata(
        barcodeDetectorMetadata(getBarcodes() + barcode)
    )

fun barcode(): Barcode =
    Barcode(
        emptyMetadata()
    )

infix fun Barcode.addText(text: String): Barcode =
    Barcode(
        metadata + (TEXT to text)
    )

infix fun Barcode.addFormat(format: String): Barcode =
    Barcode(
        metadata + (FORMAT to format)
    )

infix fun Barcode.addPage(page: Int): Barcode =
    Barcode(
        metadata + (PAGE to page)
    )

infix fun Barcode.addContourVertexOnPage(vertex: Pair<Int, Int>): Barcode =
    Barcode(
        metadata + (CONTOUR_VERTICES_ON_PAGE to getContourVerticesOnPage() + createVertex(vertex))
    )

infix fun Barcode.addContourVerticesOnPage(vertices: List<Pair<Int, Int>>): Barcode =
    Barcode(
        metadata + (CONTOUR_VERTICES_ON_PAGE to
                vertices.fold(getContourVerticesOnPage()) { accumulator, vertex -> accumulator + createVertex(vertex) })
    )

private fun createVertex(vertex: Pair<Int, Int>): Vertex =
    Vertex(emptyMetadata() + (X to vertex.first) + (Y to vertex.second))