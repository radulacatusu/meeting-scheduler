# Decode a Map 

Implement the `mapdecoder.MyMapDecoder.decode(String)` method that decodes a `String` to a corresponding `Map`.
The `String` is composed of zero or more key-value pairs. A key is separated from its value with an 'equals' character (`=`). Pairs are separated with an 'ampersand' character (`&`). 
Example: `key=value&key=value&....`

Empty keys and values are allowed, but the equals sign must be present (e.g. `"=value"`, `"key="`).

If the key or value is empty, empty `String` should be returned.

If the given `String` is empty, an empty `Map` should be returned.

If the given `String` is null, `null` should be returned.

If the given `String` has an invalid format, an `IllegalArgumentException` should be thrown.

Sample Input: `one=1&two=2`
should return a Map containing `{"one": "1", "two": "2"}`

Your solution must be implemented in the `mapdecoder.MyMapDecoder` class implementing the `MapDecoder` interface.
Please mind that you can add tests to the `MyMapDecoderTest` class if you want to self-assess your solution.
