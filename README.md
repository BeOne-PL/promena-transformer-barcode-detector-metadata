# Promena Transformer - `barcode detector` metadata
This repository contains common metadata for `barcode detector` transformers. It's built on classes from [`promena-transformer`](https://github.com/BeOne-PL/promena/tree/master/base/promena-transformer) so you don't need additional dependencies in case of serialization.

Metadata has the following elements ([`BarcodeDetectorMetadataConstants`](./src/main/kotlin/pl/beone/promena/transformer/barcodedetector/metadata/BarcodeDetectorMetadataConstants.kt) contains the list of the constants):
* `barcode`, `List<Metadata>`, mandatory
    * `text`, `String`, mandatory - the decoded text
    * `format`, `String`, optional - the name of the format
    * `page`, `Int`, optional - the index of the page (indexed from 1)
    * `contourVerticesOnPage`, `List<Metadata>`, optional 
        * `x`, `Int`, mandatory - the x position in pixels on the page
        * `y`, `Int`, mandatory - the y position in pixels on the page
        
If you want to manipulate metadata, you can use:
* [`BarcodeDetectorMetadataBuilder`](./src/main/kotlin/pl/beone/promena/transformer/barcodedetector/metadata/BarcodeDetectorMetadataBuilder.kt) - builds metadata from scratch
* [`BarcodeDetectorMetadataGetter`](./src/main/kotlin/pl/beone/promena/transformer/barcodedetector/metadata/BarcodeDetectorMetadataGetter.kt) - wraps metadata to simplify get elements