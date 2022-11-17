package com.kuflow.rest.client.util;

import static java.nio.charset.StandardCharsets.UTF_8;

import com.azure.core.util.BinaryData;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Random;

/**
 * This class provides helper methods for constructing multipart/form-data body
 */
public final class MultipartHelper {
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final char CR = (char) 0x0D;
    private static final char LF = (char) 0x0A;
    private static final String NEWLINE = "" + CR + LF;


    /**
     * Create Multipart boundary.
     *
     * @return the Multipart boundary {@link String}.
     */
    public static String getMultipartBoundary() {
        String suffix = String.format("%6x", RANDOM.nextInt(0x1000000));

        return "--------------------------" + suffix;
    }

    /**
     * Create a new  BinaryData  an App Component.
     *
     * @param fileName Name of the file, must be a valid URL character ^[a-z0-9_-]*$.
     * @param file The file as BinaryData to be used as multipart body of request.
     * @param boundary The Multipart boundary used in the request body.
     * @throws IOException thrown if error occurs while writing to byte stream.
     * @return the Multipart request body as {@link BinaryData} encoded as UTF-8.
     */
    public static BinaryData createMultipartBodyFromFile(String formDataName, String fileName, String contentType, BinaryData file, String boundary) throws IOException {
        ByteArrayOutputStream bodyByteStream = new ByteArrayOutputStream();
        bodyByteStream.write(("--" + boundary + NEWLINE).getBytes(UTF_8));
        bodyByteStream.write(("Content-Disposition: form-data; name=\"" + formDataName + "\"" + (fileName != null ? "; filename=\"" + fileName + "\"" : "" ) + NEWLINE).getBytes(UTF_8));
        bodyByteStream.write(("Content-Type: " + contentType + NEWLINE + NEWLINE).getBytes(UTF_8));
        bodyByteStream.write(file.toBytes());
        bodyByteStream.write((NEWLINE + NEWLINE + "--" + boundary + "--").getBytes(UTF_8));

        return BinaryData.fromBytes(bodyByteStream.toByteArray());
    }

}
