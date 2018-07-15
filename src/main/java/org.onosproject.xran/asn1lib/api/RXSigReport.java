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


public class RXSigReport implements Serializable {

    public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);
    private static final long serialVersionUID = 1L;
    @JsonIgnore
    public byte[] code = null;
    private PCIARFCN pciArfcn = null;
    private ECGI ecgi = null;
    private RSRPRange rsrp = null;
    private RSRQRange rsrq = null;

    public RXSigReport() {
    }

    public RXSigReport(byte[] code) {
        this.code = code;
    }

    public PCIARFCN getPciArfcn() {
        return pciArfcn;
    }

    public void setPciArfcn(PCIARFCN pciArfcn) {
        this.pciArfcn = pciArfcn;
    }

    public ECGI getEcgi() {
        return ecgi;
    }

    public void setEcgi(ECGI ecgi) {
        this.ecgi = ecgi;
    }

    public RSRPRange getRsrp() {
        return rsrp;
    }

    public void setRsrp(RSRPRange rsrp) {
        this.rsrp = rsrp;
    }

    public RSRQRange getRsrq() {
        return rsrq;
    }

    public void setRsrq(RSRQRange rsrq) {
        this.rsrq = rsrq;
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
        if (rsrq != null) {
            codeLength += rsrq.encode(os, false);
            // write tag: CONTEXT_CLASS, PRIMITIVE, 3
            os.write(0x83);
            codeLength += 1;
        }

        if (rsrp != null) {
            codeLength += rsrp.encode(os, false);
            // write tag: CONTEXT_CLASS, PRIMITIVE, 2
            os.write(0x82);
            codeLength += 1;
        }

        if (ecgi != null) {
            codeLength += ecgi.encode(os, false);
            // write tag: CONTEXT_CLASS, CONSTRUCTED, 1
            os.write(0xA1);
            codeLength += 1;
        }

        codeLength += pciArfcn.encode(os, false);
        // write tag: CONTEXT_CLASS, CONSTRUCTED, 0
        os.write(0xA0);
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
        if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 0)) {
            pciArfcn = new PCIARFCN();
            subCodeLength += pciArfcn.decode(is, false);
            if (subCodeLength == totalLength) {
                return codeLength;
            }
            subCodeLength += berTag.decode(is);
        } else {
            throw new IOException("Tag does not match the mandatory sequence element tag.");
        }

        if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 1)) {
            ecgi = new ECGI();
            subCodeLength += ecgi.decode(is, false);
            if (subCodeLength == totalLength) {
                return codeLength;
            }
            subCodeLength += berTag.decode(is);
        }

        if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 2)) {
            rsrp = new RSRPRange();
            subCodeLength += rsrp.decode(is, false);
            if (subCodeLength == totalLength) {
                return codeLength;
            }
            subCodeLength += berTag.decode(is);
        }

        if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 3)) {
            rsrq = new RSRQRange();
            subCodeLength += rsrq.decode(is, false);
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
        if (pciArfcn != null) {
            sb.append("pciArfcn: ");
            pciArfcn.appendAsString(sb, indentLevel + 1);
        } else {
            sb.append("pciArfcn: <empty-required-field>");
        }

        if (ecgi != null) {
            sb.append(",\n");
            for (int i = 0; i < indentLevel + 1; i++) {
                sb.append("\t");
            }
            sb.append("ecgi: ");
            ecgi.appendAsString(sb, indentLevel + 1);
        }

        if (rsrp != null) {
            sb.append(",\n");
            for (int i = 0; i < indentLevel + 1; i++) {
                sb.append("\t");
            }
            sb.append("rsrp: ").append(rsrp);
        }

        if (rsrq != null) {
            sb.append(",\n");
            for (int i = 0; i < indentLevel + 1; i++) {
                sb.append("\t");
            }
            sb.append("rsrq: ").append(rsrq);
        }

        sb.append("\n");
        for (int i = 0; i < indentLevel; i++) {
            sb.append("\t");
        }
        sb.append("}");
    }

}

