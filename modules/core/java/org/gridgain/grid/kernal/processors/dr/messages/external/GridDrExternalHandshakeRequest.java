/* 
 Copyright (C) GridGain Systems. All Rights Reserved.
 
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

/*  _________        _____ __________________        _____
 *  __  ____/___________(_)______  /__  ____/______ ____(_)_______
 *  _  / __  __  ___/__  / _  __  / _  / __  _  __ `/__  / __  __ \
 *  / /_/ /  _  /    _  /  / /_/ /  / /_/ /  / /_/ / _  /  _  / / /
 *  \____/   /_/     /_/   \_,__/   \____/   \__,_/  /_/   /_/ /_/
 */

package org.gridgain.grid.kernal.processors.dr.messages.external;

import org.gridgain.grid.util.typedef.internal.*;

import java.io.*;

/**
 * Request which is sent by sending receiving hub to sender hub on connection in order to
 * identify sender hub data center ID.
 */
public class GridDrExternalHandshakeRequest implements GridDrExternalProtocolVersionAware, Externalizable {
    /** Data center ID. */
    private byte dataCenterId;

    /** DR protocol version. */
    private String protoVer;

    /** Marshaller class name. */
    private String marshClsName;

    /**
     * {@link Externalizable} support.
     */
    public GridDrExternalHandshakeRequest() {
        // No-op.
    }

    /**
     * Standard constructor.
     *
     * @param dataCenterId Data center ID.
     * @param protoVer DR protocol version.
     * @param marshClsName Marshaller class name.
     */
    public GridDrExternalHandshakeRequest(byte dataCenterId, String protoVer, String marshClsName) {
        this.dataCenterId = dataCenterId;
        this.protoVer = protoVer;
        this.marshClsName = marshClsName;
    }

    /** {@inheritDoc} */
    @Override public String protocolVersion() {
        return protoVer;
    }

    /**
     * Get data center ID.
     *
     * @return Data center ID.
     */
    public byte dataCenterId() {
        return dataCenterId;
    }

    /**
     * @return Marshaller class name.
     */
    public String marshallerClassName() {
        return marshClsName;
    }

    /** {@inheritDoc} */
    @Override public void writeExternal(ObjectOutput out) throws IOException {
        out.writeByte(dataCenterId);
        U.writeString(out, protoVer);
        U.writeString(out, marshClsName);
    }

    /** {@inheritDoc} */
    @Override public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        dataCenterId = in.readByte();
        protoVer = U.readString(in);
        marshClsName = U.readString(in);
    }

    /** {@inheritDoc} */
    @Override public String toString() {
        return S.toString(GridDrExternalHandshakeRequest.class, this);
    }
}
