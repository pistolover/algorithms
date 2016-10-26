package com.protobuftest.protobuf.encode;

import java.io.IOException;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.CodedConstant;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;

public class TInteger$$JProtoBufClass
        implements com.baidu.bjf.remoting.protobuf.Codec<com.protobuftest.protobuf.encode.TInteger> {
    private com.google.protobuf.Descriptors.Descriptor descriptor;

    public byte[] encode(com.protobuftest.protobuf.encode.TInteger t) throws IOException {
        int size = 0;
        com.google.protobuf.ByteString f_2 = null;
        if (!CodedConstant.isNull(t.getName())) {
            f_2 = com.google.protobuf.ByteString.copyFromUtf8(t.getName());
        }
        if (!CodedConstant.isNull(t.getName())) {
            size += com.google.protobuf.CodedOutputStream.computeBytesSize(2, f_2);
        }
        final byte[] result = new byte[size];
        final CodedOutputStream output = CodedOutputStream.newInstance(result);
        if (f_2 != null) {
            output.writeBytes(2, f_2);
        }
        return result;
    }

    public com.protobuftest.protobuf.encode.TInteger decode(byte[] bb) throws IOException {
        com.protobuftest.protobuf.encode.TInteger ret = new com.protobuftest.protobuf.encode.TInteger();
        CodedInputStream input = CodedInputStream.newInstance(bb, 0, bb.length);
        try {
            boolean done = false;
            Codec codec = null;
            while (!done) {
                int tag = input.readTag();
                if (tag == 0) {
                    break;
                }
                if (tag == 18) {
                    ret.setName(input.readString());
                    continue;
                }
                input.skipField(tag);
            }
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
            throw e;
        } catch (java.io.IOException e) {
            throw e;
        }
        return ret;
    }

    public int size(com.protobuftest.protobuf.encode.TInteger t) throws IOException {
        int size = 0;
        com.google.protobuf.ByteString f_2 = null;
        if (!CodedConstant.isNull(t.getName())) {
            f_2 = com.google.protobuf.ByteString.copyFromUtf8(t.getName());
        }
        if (!CodedConstant.isNull(t.getName())) {
            size += com.google.protobuf.CodedOutputStream.computeBytesSize(2, f_2);
        }
        return size;
    }

    public void writeTo(com.protobuftest.protobuf.encode.TInteger t, CodedOutputStream output) throws IOException {
        com.google.protobuf.ByteString f_2 = null;
        if (!CodedConstant.isNull(t.getName())) {
            f_2 = com.google.protobuf.ByteString.copyFromUtf8(t.getName());
        }
        if (f_2 != null) {
            output.writeBytes(2, f_2);
        }
    }

    public com.protobuftest.protobuf.encode.TInteger readFrom(CodedInputStream input) throws IOException {
        com.protobuftest.protobuf.encode.TInteger ret = new com.protobuftest.protobuf.encode.TInteger();
        try {
            boolean done = false;
            Codec codec = null;
            while (!done) {
                int tag = input.readTag();
                if (tag == 0) {
                    break;
                }
                if (tag == 18) {
                    ret.setName(input.readString());
                    continue;
                }
                input.skipField(tag);
            }
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
            throw e;
        } catch (java.io.IOException e) {
            throw e;
        }
        return ret;
    }

    public com.google.protobuf.Descriptors.Descriptor getDescriptor() throws IOException {
        if (this.descriptor != null) {
            return this.descriptor;
        }
        com.google.protobuf.Descriptors.Descriptor descriptor = CodedConstant
                .getDescriptor(com.protobuftest.protobuf.encode.TInteger.class);
        return (this.descriptor = descriptor);
    }
}
