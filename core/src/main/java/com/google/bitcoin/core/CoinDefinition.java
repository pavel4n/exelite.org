package com.google.bitcoin.core;

import java.math.BigInteger;
import java.util.Date;
import java.util.Map;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: HashEngineering
 * Date: 8/13/13
 * Time: 7:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class CoinDefinition {


    public static final String coinName = "Execoin";
    public static final String coinTicker = "EXE";
    public static final String coinURIScheme = "execoin";
//    public static final String cryptsyMarketId = "45";
//    public static final String cryptsyMarketCurrency = "BTC";


    public static final String BLOCKEXPLORER_BASE_URL_PROD = "http://explorer.execoin.net/";
    public static final String BLOCKEXPLORER_BASE_URL_TEST = "http://explorer.execoin.net/";

    public static final String DONATION_ADDRESS = "EZF3TXHFEwK58cjESPewcPHk9pZ5M6nDBF";  //To be changed to HashEngineering donation EXE address

    enum CoinHash {
        SHA256,
        scrypt
    };
    public static final CoinHash coinHash = CoinHash.scrypt;
    //Original Values
    public static final int TARGET_TIMESPAN_0 = (int)(1 * 24 * 60 * 60);  // 1 day per difficulty cycle, on average.
    public static final int TARGET_SPACING_0 = (int)(45);  // 45 seconds per block.
    public static final int INTERVAL_0 = TARGET_TIMESPAN_0 / TARGET_SPACING_0;  //1920 blocks

    public static final int TARGET_TIMESPAN = (int)(1 * 24 * 60 * 60);  // 1 day per difficulty cycle, on average.
    public static final int TARGET_SPACING = (int)(45);  // 45 seconds per block.
    public static final int INTERVAL = TARGET_TIMESPAN / TARGET_SPACING;  //1920 blocks

    public static final int TARGET_TIMESPAN_3 = (int)(1 * 24 * 60 * 60);  // 1 day per difficulty cycle, on average.
    public static final int TARGET_SPACING_3 = (int)(45);  // 45 seconds per block.
    public static final int INTERVAL_3 = TARGET_TIMESPAN_3 / TARGET_SPACING_3;  //1920 blocks

    static final long nTargetSpacing = 45; // 45 seconds
    static final long nOriginalInterval = 1920;
    static final long nFilteredInterval = 1920;
    static final long nOriginalTargetTimespan = nOriginalInterval * nTargetSpacing; // 1 day
    static final long nFilteredTargetTimespan = nFilteredInterval * nTargetSpacing; // 1 day

    public static int DIFF_FILTER_THRESHOLD_TESTNET = 43847;
    public static int DIFF_FILTER_THRESHOLD = 43847;

    public static int nDifficultySwitchHeight = 43847;
    public static int nDifficultySwitchHeightTwo = 9999999;

    public static final int getInterval(int height, boolean testNet) {
        if(height < nDifficultySwitchHeight)
            return (int)nOriginalInterval;    //1080
        else if(height < nDifficultySwitchHeightTwo)
            return (int)nFilteredInterval;      //108
        else return INTERVAL_3;
    }
    public static final int getIntervalForCheckpoints(int height, boolean testNet) {
        if(height < 43847)
            return (int)nOriginalInterval;    //1920
//        else if(height < nDifficultySwitchHeightTwo)
//            return (int)nOriginalInterval;      //2016
        else return (int)nOriginalInterval / 4; //480
    }
    public static final int getTargetTimespan(int height, boolean testNet) {
        if(height < nDifficultySwitchHeight)
            return TARGET_TIMESPAN_0;  //1 day
        else
            return TARGET_TIMESPAN;    //1 day
    }
    public static int getMaxTimeSpan(int value, int height, boolean testNet)
    {
        if(height < nDifficultySwitchHeight)
            return value * 4;
        else
            return value * 1;   // not used
    }
    public static int getMinTimeSpan(int value, int height, boolean testNet)
    {
        if(height < nDifficultySwitchHeight)
            return value / 4;
        else
            return value * 1;    //not used
    }
    public static int spendableCoinbaseDepth = 100; //main.h: static const int COINBASE_MATURITY
    public static final int MAX_MONEY = 84000000;                 //main.h:  MAX_MONEY
    public static final String MAX_MONEY_STRING = "84000000";     //main.h:  MAX_MONEY

    public static final BigInteger DEFAULT_MIN_TX_FEE = BigInteger.valueOf(100000);   // MIN_TX_FEE
    public static final BigInteger DUST_LIMIT = Utils.CENT; //main.h CTransaction::GetMinFee        0.01 coins

    public static final int PROTOCOL_VERSION = 70002;          //version.h PROTOCOL_VERSION
    public static final int MIN_PROTOCOL_VERSION = 209;        //version.h MIN_PROTO_VERSION

    public static final boolean supportsBloomFiltering = true; //Requires PROTOCOL_VERSION 70000 in the client

    public static final int Port    = 9989;       //protocol.h GetDefaultPort(testnet=false)
    public static final int TestPort = 19989;     //protocol.h GetDefaultPort(testnet=true)

    //
    //  Production
    //
    public static final int AddressHeader = 33;             //base58.h CBitcoinAddress::PUBKEY_ADDRESS
    public static final int p2shHeader = 5;             //base58.h CBitcoinAddress::SCRIPT_ADDRESS

    public static final int dumpedPrivateKeyHeader = 128;   //common to all coins
    public static final long PacketMagic = 0xfabfb5da;      //0xfb, 0xc0, 0xb6, 0xdb

    //Genesis Block Information from main.cpp: LoadBlockIndex
    static public long genesisBlockDifficultyTarget = (0x1e0ffff0L);         //main.cpp: LoadBlockIndex
    static public long genesisBlockTime = 1390959880L;                       //main.cpp: LoadBlockIndex
    static public long genesisBlockNonce = (2084845118);                         //main.cpp: LoadBlockIndex
    static public String genesisHash = "b9429871ab336e52be203367d341133d03e446c7e05be3ff3179bdaa797b2c71"; //main.cpp: hashGenesisBlock
    static public int genesisBlockValue = 50;                                                              //main.cpp: LoadBlockIndex
    //taken from the raw data of the block explorer
    static public String genesisXInBytes = "0002e7034c4c4a414e2e2032382c20323031342050657465205365656765722c20536f6e6777726974657220616e64204368616d70696f6e206f6620466f6c6b204d757369632c2044696573206174203934";   //"JAN. 28, 2014 Pete Seeger, Songwriter and Champion of Folk Music, Dies at 94"
    static public String genessiXOutBytes = "";

    //net.cpp strDNSSeed
    static public String[] dnsSeeds = new String[] {
            "dnsseed.execoin.net"
    };

    //
    // TestNet - execoin - not tested
    //
    public static final boolean supportsTestNet = false;
    public static final int testnetAddressHeader = 111;             //base58.h CBitcoinAddress::PUBKEY_ADDRESS_TEST
    public static final int testnetp2shHeader = 196;             //base58.h CBitcoinAddress::SCRIPT_ADDRESS_TEST
    public static final long testnetPacketMagic = 0xfdf0f4fe;      //0xfc, 0xc1, 0xb7, 0xdc
    public static final String testnetGenesisHash = "5e039e1ca1dbf128973bf6cff98169e40a1b194c3b91463ab74956f413b2f9c8";
    static public long testnetGenesisBlockDifficultyTarget = (0x1e0ffff0L);         //main.cpp: LoadBlockIndex
    static public long testnetGenesisBlockTime = 1369198853L;                       //main.cpp: LoadBlockIndex
    static public long testnetGenesisBlockNonce = (386245382);                         //main.cpp: LoadBlockIndex





    //main.cpp GetBlockValue(height, fee)
    public static BigInteger GetBlockReward(int height)
    {
      return Utils.toNanoCoins(0, 50).shiftRight(height / subsidyDecreaseBlockCount);
    }

    public static int subsidyDecreaseBlockCount = 840000;     //main.cpp GetBlockValue(height, fee)

    public static BigInteger proofOfWorkLimit = Utils.decodeCompactBits(0x1e0fffffL);  //main.cpp bnProofOfWorkLimit (~uint256(0) >> 20); // execoin: starting difficulty is 1 / 2^12

    static public String[] testnetDnsSeeds = new String[] {
          "not supported"
    };
    //from main.h: CAlert::CheckSignature
    public static final String SATOSHI_KEY = "040184710fa689ad5023690c80f3a49c8f13f8d45b8c857fbcbc8bc4a8e4d3eb4b10f4d4604fa08dce601aaf0f470216fe1b51850b4acf21b179c45070ac7b03a9";
    public static final String TESTNET_SATOSHI_KEY = "04302390343f91cc401d56d68b123028bf52e5fca1939df127f63c6467cdf9c8e2c14b61104cf817d0b780da337893ecc4aaff1309e536162dabbdb45200ca2b0a";

    /** The string returned by getId() for the main, production network where people trade things. */
    public static final String ID_MAINNET = "org.execoin.production";
    /** The string returned by getId() for the testnet. */
    public static final String ID_TESTNET = "org.execoin.test";
    /** Unit test network. */
    public static final String ID_UNITTESTNET = "com.google.execoin.unittest";

    //checkpoints.cpp Checkpoints::mapCheckpoints
    public static void initCheckpoints(Map<Integer, Sha256Hash> checkpoints)
    {
        checkpoints.put(  34000, new Sha256Hash("9b7ecc4ea9b413cce66b1f7b37b2e86ea716ee6781be4fe87fc64dde5bd19551"));
		checkpoints.put(  77000, new Sha256Hash("66bef8487d46570d499f1a517868ddc6333fe2d5e5681fb7bd68c4a957e15c7b"));
    }


}
