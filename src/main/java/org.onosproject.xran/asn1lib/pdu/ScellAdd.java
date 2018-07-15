/**
 * This class file was automatically generated by jASN1 v1.8.2 (http://www.openmuc.org)
 */

package org.onosproject.xran.asn1lib.pdu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import org.onosproject.xran.asn1lib.api.CRNTI;
import org.onosproject.xran.asn1lib.api.ECGI;
import org.onosproject.xran.asn1lib.api.PropScell;
import org.onosproject.xran.asn1lib.ber.BerByteArrayOutputStream;
import org.onosproject.xran.asn1lib.ber.BerLength;
import org.onosproject.xran.asn1lib.ber.BerTag;
import org.onosproject.xran.asn1lib.ber.types.BerBoolean;
import org.onosproject.xran.asn1lib.ber.types.BerEnum;
import org.onosproject.xran.asn1lib.ber.types.BerInteger;
import org.onosproject.xran.asn1lib.ber.types.string.BerUTF8String;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ScellAdd implements Serializable {

    public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);
    private static final long serialVersionUID = 1L;
    @JsonIgnore
    public byte[] code = null;
    private CRNTI crnti = null;
    private ECGI ecgi = null;
    private ScellsProp scellsProp = null;

    public ScellAdd() {
    }

    public ScellAdd(byte[] code) {
        this.code = code;
    }

    public static XrancPdu constructPacket(ECGI ecgi, CRNTI crnti, PropScell propScell) {
        ScellAdd scellAdd = new ScellAdd();
        scellAdd.setCrnti(crnti);
        scellAdd.setEcgi(ecgi);

        propScell.setCrossCarrierSchedEnable(new BerBoolean(true));
        propScell.setCaDirection(new BerEnum(new BigInteger("dl")));
        propScell.setDeactTimer(new BerInteger(1000));
        ScellsProp scellsProp = new ScellsProp();
        scellsProp.getPropScell().add(propScell);
        scellAdd.setScellsProp(scellsProp);

        XrancPduBody body = new XrancPduBody();
        body.setScellAdd(scellAdd);

        BerUTF8String ver = null;
        try {
            ver = new BerUTF8String("5");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        XrancApiID apiID = new XrancApiID(24);
        XrancPduHdr hdr = new XrancPduHdr();
        hdr.setVer(ver);
        hdr.setApiId(apiID);

        XrancPdu pdu = new XrancPdu();
        pdu.setHdr(hdr);
        pdu.setBody(body);

        return pdu;
    }

    public CRNTI getCrnti() {
        return crnti;
    }

    public void setCrnti(CRNTI crnti) {
        this.crnti = crnti;
    }

    public ECGI getEcgi() {
        return ecgi;
    }

    public void setEcgi(ECGI ecgi) {
        this.ecgi = ecgi;
    }

    public ScellsProp getScellsProp() {
        return scellsProp;
    }

    public void setScellsProp(ScellsProp scellsProp) {
        this.scellsProp = scellsProp;
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
        codeLength += scellsProp.encode(os, false);
        // write tag: CONTEXT_CLASS, CONSTRUCTED, 2
        os.write(0xA2);
        codeLength += 1;

        codeLength += ecgi.encode(os, false);
        // write tag: CONTEXT_CLASS, CONSTRUCTED, 1
        os.write(0xA1);
        codeLength += 1;

        codeLength += crnti.encode(os, false);
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
            crnti = new CRNTI();
            subCodeLength += crnti.decode(is, false);
            subCodeLength += berTag.decode(is);
        } else {
            throw new IOException("Tag does not match the mandatory sequence element tag.");
        }

        if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 1)) {
            ecgi = new ECGI();
            subCodeLength += ecgi.decode(is, false);
            subCodeLength += berTag.decode(is);
        } else {
            throw new IOException("Tag does not match the mandatory sequence element tag.");
        }

        if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 2)) {
            scellsProp = new ScellsProp();
            subCodeLength += scellsProp.decode(is, false);
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
        if (crnti != null) {
            sb.append("crnti: ").append(crnti);
        } else {
            sb.append("crnti: <empty-required-field>");
        }

        sb.append(",\n");
        for (int i = 0; i < indentLevel + 1; i++) {
            sb.append("\t");
        }
        if (ecgi != null) {
            sb.append("ecgi: ");
            ecgi.appendAsString(sb, indentLevel + 1);
        } else {
            sb.append("ecgi: <empty-required-field>");
        }

        sb.append(",\n");
        for (int i = 0; i < indentLevel + 1; i++) {
            sb.append("\t");
        }
        if (scellsProp != null) {
            sb.append("scellsProp: ");
            scellsProp.appendAsString(sb, indentLevel + 1);
        } else {
            sb.append("scellsProp: <empty-required-field>");
        }

        sb.append("\n");
        for (int i = 0; i < indentLevel; i++) {
            sb.append("\t");
        }
        sb.append("}");
    }

    public static class ScellsProp implements Serializable {

        public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);
        private static final long serialVersionUID = 1L;
        @JsonIgnore
        public byte[] code = null;
        private List<PropScell> seqOf = null;

        public ScellsProp() {
            seqOf = new ArrayList<PropScell>();
        }

        public ScellsProp(byte[] code) {
            this.code = code;
        }

        @JsonValue
        public List<PropScell> getPropScell() {
            if (seqOf == null) {
                seqOf = new ArrayList<PropScell>();
            }
            return seqOf;
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
            for (int i = (seqOf.size() - 1); i >= 0; i--) {
                codeLength += seqOf.get(i).encode(os, true);
            }

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
            if (withTag) {
                codeLength += tag.decodeAndCheck(is);
            }

            BerLength length = new BerLength();
            codeLength += length.decode(is);
            int totalLength = length.val;

            while (subCodeLength < totalLength) {
                PropScell element = new PropScell();
                subCodeLength += element.decode(is, true);
                seqOf.add(element);
            }
            if (subCodeLength != totalLength) {
                throw new IOException("Decoded SequenceOf or SetOf has wrong length. Expected " + totalLength + " but has " + subCodeLength);

            }
            codeLength += subCodeLength;

            return codeLength;
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

            sb.append("{\n");
            for (int i = 0; i < indentLevel + 1; i++) {
                sb.append("\t");
            }
            if (seqOf == null) {
                sb.append("null");
            } else {
                Iterator<PropScell> it = seqOf.iterator();
                if (it.hasNext()) {
                    it.next().appendAsString(sb, indentLevel + 1);
                    while (it.hasNext()) {
                        sb.append(",\n");
                        for (int i = 0; i < indentLevel + 1; i++) {
                            sb.append("\t");
                        }
                        it.next().appendAsString(sb, indentLevel + 1);
                    }
                }
            }

            sb.append("\n");
            for (int i = 0; i < indentLevel; i++) {
                sb.append("\t");
            }
            sb.append("}");
        }

    }

}

