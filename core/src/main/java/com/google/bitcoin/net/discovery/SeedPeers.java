/**
 * Copyright 2011 Micheal Swiggs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.bitcoin.net.discovery;

import com.google.bitcoin.core.NetworkParameters;

import javax.annotation.Nullable;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

/**
 * SeedPeers stores a pre-determined list of Bitcoin node addresses. These nodes are selected based on being
 * active on the network for a long period of time. The intention is to be a last resort way of finding a connection
 * to the network, in case IRC and DNS fail. The list comes from the Bitcoin C++ source code.
 */
public class SeedPeers implements PeerDiscovery {
    private NetworkParameters params;
    private int pnseedIndex;

    public SeedPeers(NetworkParameters params) {
        this.params = params;
    }

    /**
     * Acts as an iterator, returning the address of each node in the list sequentially.
     * Once all the list has been iterated, null will be returned for each subsequent query.
     *
     * @return InetSocketAddress - The address/port of the next node.
     * @throws PeerDiscoveryException
     */
    @Nullable
    public InetSocketAddress getPeer() throws PeerDiscoveryException {
        try {
            return nextPeer();
        } catch (UnknownHostException e) {
            throw new PeerDiscoveryException(e);
        }
    }

    @Nullable
    private InetSocketAddress nextPeer() throws UnknownHostException {
        if (pnseedIndex >= seedAddrs.length) return null;
        return new InetSocketAddress(convertAddress(seedAddrs[pnseedIndex++]),
                params.getPort());
    }

    /**
     * Returns an array containing all the Bitcoin nodes within the list.
     */
    public InetSocketAddress[] getPeers(long timeoutValue, TimeUnit timeoutUnit) throws PeerDiscoveryException {
        try {
            return allPeers();
        } catch (UnknownHostException e) {
            throw new PeerDiscoveryException(e);
        }
    }

    private InetSocketAddress[] allPeers() throws UnknownHostException {
        InetSocketAddress[] addresses = new InetSocketAddress[seedAddrs.length];
        for (int i = 0; i < seedAddrs.length; ++i) {
            addresses[i] = new InetSocketAddress(convertAddress(seedAddrs[i]), params.getPort());
        }
        return addresses;
    }

    private InetAddress convertAddress(int seed) throws UnknownHostException {
        byte[] v4addr = new byte[4];
        v4addr[0] = (byte) (0xFF & (seed));
        v4addr[1] = (byte) (0xFF & (seed >> 8));
        v4addr[2] = (byte) (0xFF & (seed >> 16));
        v4addr[3] = (byte) (0xFF & (seed >> 24));
        return InetAddress.getByAddress(v4addr);
    }

    public static int[] seedAddrs =
            {
                    0x25045065, 0x5B4E7620, 0x67F0C20A, 0x474450FD, 0xC7C8660C, 0x4CFD8658, 0xAF2CD948, 0x555FE42F,
					0xA2F3AFA5, 0x4E2E2C74, 0x57ECC44D, 0x17160145, 0x44E2827D, 0x57C8DFCE, 0xA2EA4288, 0x707C6B8F,
					0x3B3EE50A, 0x94FB0C7C, 0x44033535, 0xC07E764D, 0x36550C98, 0x5F551AC5, 0x6BAAF01E, 0xA2F3888E,
					0x80C7E9B3, 0xC6D36440, 0xC0F198C6, 0x6BAA4F0A, 0x258B03CE, 0xBCE2A9BC, 0x6BAAE3AF, 0x80C7DD6D,
					0x6BAAE3B0, 0xBCE2A9BD, 0xC0F180D7, 0x6BAA4F0C, 0x6BAA0CAE, 0xBCE282CD, 0x80C7EA1C, 0x0587A129,
					0x253B06B1, 0xC063224F, 0xA2F3674B, 0xC06304BD
            };
    
    public void shutdown() {
    }
}
