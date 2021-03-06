/**
 * This class file was automatically generated by jASN1 v1.8.2 (http://www.openmuc.org)
 */

package org.onosproject.xran.asn1lib.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.onosproject.xran.asn1lib.ber.BerByteArrayOutputStream;
import org.onosproject.xran.asn1lib.ber.BerLength;
import org.onosproject.xran.asn1lib.ber.BerTag;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;


public class MeasObject implements Serializable {

    public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);
    private static final long serialVersionUID = 1L;
    @JsonIgnore
    public byte[] code = null;
    private ARFCNValue dlFreq = null;
    private MeasCells measCells = null;
    public MeasObject() {
    }

    public MeasObject(byte[] code) {
        this.code = code;
    }

    public ARFCNValue getDlFreq() {
        return dlFreq;
    }

    public void setDlFreq(ARFCNValue dlFreq) {
        this.dlFreq = dlFreq;
    }

    public MeasCells getMeasCells() {
        return measCells;
    }

    public void setMeasCells(MeasCells measCells) {
        this.measCells = measCells;
    }

    public int encode(BerByteArrayOutputStream os) throws IOException {
        return encode(os, true);
    }

    public int encode(BerByteArrayOutputStream os, boolean withTag) throws IOException {

        if (code != null) {
            for (int i = code.length - 1; i >= 0; i--) {
                os.write(code[i]);
            }
            if (withTag) {
                return tag.encode(os) + code.length;
            }
            return code.length;
        }

        int codeLength = 0;
        if (measCells != null) {
            codeLength += measCells.encode(os, false);
            // write tag: CONTEXT_CLASS, CONSTRUCTED, 1
            os.write(0xA1);
            codeLength += 1;
        }

        codeLength += dlFreq.encode(os, false);
        // write tag: CONTEXT_CLASS, PRIMITIVE, 0
        os.write(0x80);
        codeLength += 1;

        codeLength += BerLength.encodeLength(os, codeLength);

        if (withTag) {
            codeLength += tag.encode(os);
        }

        return codeLength;

    }

    public int decode(InputStream is) throws IOException {
        return decode(is, true);
    }

    public int decode(InputStream is, boolean withTag) throws IOException {
        int codeLength = 0;
        int subCodeLength = 0;
        BerTag berTag = new BerTag();

        if (withTag) {
            codeLength += tag.decodeAndCheck(is);
        }

        BerLength length = new BerLength();
        codeLength += length.decode(is);

        int totalLength = length.val;
        codeLength += totalLength;

        subCodeLength += berTag.decode(is);
        if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 0)) {
            dlFreq = new ARFCNValue();
            subCodeLength += dlFreq.decode(is, false);
            if (subCodeLength == totalLength) {
                return codeLength;
            }
            subCodeLength += berTag.decode(is);
        } else {
            throw new IOException("Tag does not match the mandatory sequence element tag.");
        }

        if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 1)) {
            measCells = new MeasCells();
            subCodeLength += measCells.decode(is, false);
            if (subCodeLength == totalLength) {
                return codeLength;
            }
        }
        throw new IOException("Unexpected end of sequence, length tag: " + totalLength + ", actual sequence length: " + subCodeLength);


    }

    public void encodeAndSave(int encodingSizeGuess) throws IOException {
        BerByteArrayOutputStream os = new BerByteArrayOutputStream(encodingSizeGuess);
        encode(os, false);
        code = os.getArray();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        appendAsString(sb, 0);
        return sb.toString();
    }

    public void appendAsString(StringBuilder sb, int indentLevel) {

        sb.append("{");
        sb.append("\n");
        for (int i = 0; i < indentLevel + 1; i++) {
            sb.append("\t");
        }
        if (dlFreq != null) {
            sb.append("dlFreq: ").append(dlFreq);
        } else {
            sb.append("dlFreq: <empty-required-field>");
        }

        if (measCells != null) {
            sb.append(",\n");
            for (int i = 0; i < indentLevel + 1; i++) {
                sb.append("\t");
            }
            sb.append("measCells: ");
            measCells.appendAsString(sb, indentLevel + 1);
        }

        sb.append("\n");
        for (int i = 0; i < indentLevel; i++) {
            sb.append("\t");
        }
        sb.append("}");
    }

    public static class MeasCells implements Serializable {

        public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);
        private static final long serialVersionUID = 1L;
        @JsonIgnore
        public byte[] code = null;
        private PhysCellId pci = null;
        private QOffsetRange cellIndividualOffset = null;

        public MeasCells() {
        }

        public MeasCells(byte[] code) {
            this.code = code;
        }

        public PhysCellId getPci() {
            return pci;
        }

        public void setPci(PhysCellId pci) {
            this.pci = pci;
        }

        public QOffsetRange getCellIndividualOffset() {
            return cellIndividualOffset;
        }

        public void setCellIndividualOffset(QOffsetRange cellIndividualOffset) {
            this.cellIndividualOffset = cellIndividualOffset;
        }

        public int encode(BerByteArrayOutputStream os) throws IOException {
            return encode(os, true);
        }

        public int encode(BerByteArrayOutputStream os, boolean withTag) throws IOException {

            if (code != null) {
                for (int i = code.length - 1; i >= 0; i--) {
                    os.write(code[i]);
                }
                if (withTag) {
                    return tag.encode(os) + code.length;
                }
                return code.length;
            }

            int codeLength = 0;
            if (cellIndividualOffset != null) {
                codeLength += cellIndividualOffset.encode(os, false);
                // write tag: CONTEXT_CLASS, PRIMITIVE, 1
                os.write(0x81);
                codeLength += 1;
            }

            codeLength += pci.encode(os, false);
            // write tag: CONTEXT_CLASS, PRIMITIVE, 0
            os.write(0x80);
            codeLength += 1;

            codeLength += BerLength.encodeLength(os, codeLength);

            if (withTag) {
                codeLength += tag.encode(os);
            }

            return codeLength;

        }

        public int decode(InputStream is) throws IOException {
            return decode(is, true);
        }

        public int decode(InputStream is, boolean withTag) throws IOException {
            int codeLength = 0;
            int subCodeLength = 0;
            BerTag berTag = new BerTag();

            if (withTag) {
                codeLength += tag.decodeAndCheck(is);
            }

            BerLength length = new BerLength();
            codeLength += length.decode(is);

            int totalLength = length.val;
            codeLength += totalLength;

            subCodeLength += berTag.decode(is);
            if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 0)) {
                pci = new PhysCellId();
                subCodeLength += pci.decode(is, false);
                if (subCodeLength == totalLength) {
                    return codeLength;
                }
                subCodeLength += berTag.decode(is);
            } else {
                throw new IOException("Tag does not match the mandatory sequence element tag.");
            }

            if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 1)) {
                cellIndividualOffset = new QOffsetRange();
                subCodeLength += cellIndividualOffset.decode(is, false);
                if (subCodeLength == totalLength) {
                    return codeLength;
                }
            }
            throw new IOException("Unexpected end of sequence, length tag: " + totalLength + ", actual sequence length: " + subCodeLength);


        }

        public void encodeAndSave(int encodingSizeGuess) throws IOException {
            BerByteArrayOutputStream os = new BerByteArrayOutputStream(encodingSizeGuess);
            encode(os, false);
            code = os.getArray();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            appendAsString(sb, 0);
            return sb.toString();
        }

        public void appendAsString(StringBuilder sb, int indentLevel) {

            sb.append("{");
            sb.append("\n");
            for (int i = 0; i < indentLevel + 1; i++) {
                sb.append("\t");
            }
            if (pci != null) {
                sb.append("pci: ").append(pci);
            } else {
                sb.append("pci: <empty-required-field>");
            }

            if (cellIndividualOffset != null) {
                sb.append(",\n");
                for (int i = 0; i < indentLevel + 1; i++) {
                    sb.append("\t");
                }
                sb.append("cellIndividualOffset: ").append(cellIndividualOffset);
            }

            sb.append("\n");
            for (int i = 0; i < indentLevel; i++) {
                sb.append("\t");
            }
            sb.append("}");
        }

    }

}

