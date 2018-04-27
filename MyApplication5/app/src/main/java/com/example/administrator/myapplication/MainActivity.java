package com.example.administrator.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import org.ethereum.geth.*;
import org.ethereum.geth.Geth.*;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    TextView tx = null;
    Context ctx = null;
    Node node = null;
    String RootDir = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ctx = new Context();
        final Button start1    = (Button) findViewById(R.id.button1);
        final TextView label1    = (TextView) findViewById(R.id.textView);
        tx = label1;

        RootDir = "/mnt/sdcard/work/myworkpre1";

        final Button start2    = (Button) findViewById(R.id.button2);
        final Button start3    = (Button) findViewById(R.id.button3);



//        try {
//            node = Geth.newNode(getFilesDir() + "/.ethereum1", new NodeConfig());
//            node.start();
//
//            NodeInfo info = node.getNodeInfo();
//            Log.d("111", "onCreate: My name: " + info.getName() + "\n");
//            Log.d("111","My protocols: " + info.getProtocols() + "\n\n");
//
//            EthereumClient ec = node.getEthereumClient();
//            Log.d("111","Latest block: " + ec.getBlockByNumber(ctx, -1).getNumber() + ", syncing...\n");
//
            //NewHeadHandler handler = new NewHeadHandler() {
//                @Override
//                public void onError(String error) {
//                }
//
//                @Override
//                public void onNewHead(final Header header) {
////                    Main2Activity.this.runOnUiThread(new Runnable() {
////                        public void run() {
//                    Log.d("111","#" + header.getNumber() + ": " + header.getHash().getHex().substring(0, 10) + "…\n");
//                    tx.setText("#" + header.getNumber() + ": " + header.getHash().getHex().substring(0, 10) + "…\n");
////                        }
////                    });
//                }
//            };
//            ec.subscribeNewHead(ctx, handler, 16);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        try {
            android.util.Log.d("before", "yay");
//            NodeConfig nc = new NodeConfig();
//            nc.setEthereumNetworkID(1);
//            nc.setEthereumDatabaseCache(1024);
////            nc.setEthereumGenesis(Geth.testnetGenesis());
//
//            node = Geth.newNode(getFilesDir() + "/test111", nc);
//            node.start();
////            NodeHolder nh = NodeHolder.getInstance();
////            nh.setNode(node);
//            KeyStore ks = new KeyStore(getFilesDir() + "/keystore111", Geth.LightScryptN, Geth.LightScryptP);
//            Account newAcc = ks.newAccount("reallyhardpassword");
//            Account newAcc = ks.importKey(keyfile.getBytes(), "alsohardpassword", "reallyhardpassword");
//            byte[] bytes = ks.exportKey(newAcc, "reallyhardpassword", "alsohardpassword");
//            android.util.Log.d("keyfile", newAcc.getAddress().getHex());
//            nh.setAcc(newAcc);
            Log.d("success", "this worked");
        } catch (Exception e) {
            Log.d("fail", "what happened?" + e.getMessage());
            e.printStackTrace();
        }


        start1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                InitClient();
            }
        });

        start2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TestFunction1();
            }
        });

        start3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TestFunction2();
            }
        });
    }
//    var defaultNodeConfig = &NodeConfig{
//        BootstrapNodes:        FoundationBootnodes(),
//                MaxPeers:              25,
//                EthereumEnabled:       true,
//                EthereumNetworkID:     1,
//                EthereumDatabaseCache: 16,
//    }
    void InitClient()
    {
        try {
            File file = new File(RootDir);
            //新建一个File，传入文件夹目录
//判断文件夹是否存在，如果不存在就创建，否则不创建
            if (!file.exists()) {
                //通过file的mkdirs()方法创建<span style="color:#FF0000;">目录中包含却不存在</span>的文件夹
                if(file.mkdirs() == true){
                    Log.d("111", "InitClient111: " + 321321);
                }else
                {
                    Log.d("111", "InitClient111: " + 11322);
                }
            }else
            {
                Log.d("111", "InitClient111: " + 11323332);

            }

            tx.setText("syncing...");
            NodeConfig nc = new NodeConfig();
            String GenesisString ="{\"config\":{\"chainId\":15,\"homesteadBlock\":0,\"eip155Block\":0,\"eip158Block\":0},\"difficulty\":\"200\",\"gasLimit\":\"2100000\",\"alloc\":{\"7df9a875a174b3bc565e6424a0050ebc1b2d1d82\":{\"balance\":\"300000\"},\"f41c74c9ae680c1aa78f42e5647a62f353b7bdde\":{\"balance\":\"400000\"}}}";
            nc.setEthereumGenesis(GenesisString);
            //nc.setEthereumGenesis(Geth.testnetGenesis());
            nc.setEthereumEnabled(true);
            nc.setEthereumNetworkID(2264);
            // WhisperEnabled specifies whether the node should run the Whisper protocol.
            //nc.setWhisperEnabled(false);
            // EthereumEnabled specifies whether the node should run the Ethereum protocol.

//            nc.setEthereumNetworkID(1);

            node = Geth.newNode(RootDir,nc);
            node.start();
            EthereumClient ec = node.getEthereumClient();
            String peerInfoString = node.getPeersInfo().toString();
//            String hBlock = ec.syncProgress(ctx).getHighestBlock();
            //String dir = node.getPeersInfo().toString();
            //BigInt n = ec.suggestGasPrice(ctx);
            //long a = ec.getBlockByNumber(ctx,-1).getNumber();
            //long b = a;
            tx.setText("Geth not syncing..."+node.getNodeInfo().getListenerAddress() +"\n");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    void TestFunction1()
    {
        TestNewAddress();
        //TestContract();
        String nodeName = node.getNodeInfo().getName();
        Log.d("111", "testFunction1 " + nodeName );
    }
    void TestFunction2()
    {
//        GetAccountInfo();
        TestGetContract();
    }

    void TestNewAddress()
    {
//        KeyStore ks = new KeyStore(RootDir,Geth.LightScryptN,Geth.LightScryptP);
//        try{
//            Account newAcc = ks.newAccount("111111");
//            tx.setText("account : "+ks.getAccounts().get(0).getAddress().getHex());
//        } catch (Exception e) {
//
//        }

        BigInt b = getBalanceAt("0x5a9BEacB01bC817bf382037120C58FAe686DE9B6");
        Log.d("111","first  balance account is " + b);

        GetAccountInfo();
    }
    void GetAccountInfo()
    {
         KeyStore ks = new KeyStore(RootDir,Geth.LightScryptN,Geth.LightScryptP);
        Accounts as = ks.getAccounts();
        tx.append("account : "+ks.getAccounts().size() + "\n");
        if(as.size()>0) {
//            for(int i = 0;i<as.size();++i)
//            {
//
//                try {
//                    Account acc = as.get(i);
//                    //  tx.append("account : "+ "i" +acc.getURL() + "\n");
//                    tx.append("index: " + i +" " +  acc.getAddress().getHex()  + "\n");
//                    Log.d("111", "TestFunction2: " + acc.getAddress().getHex());
//
//                } catch (Exception e)
//                {
//                    tx.append("account : "+ "i" + "error " + "\n");
//                }
//            }
//
//            try {
//                EthereumClient ec = node.getEthereumClient();
//
//                if(ec.syncProgress(ctx) !=null)
//                {
//                    long highestblock = ec.syncProgress(ctx).getHighestBlock();
//                    Log.d("111", "TestFunction highestblock : " + highestblock);
//                    long currentblock = ec.syncProgress(ctx).getCurrentBlock();
//                    Log.d("111", "TestFunction currentblock : " + currentblock);
//
//                }
//                BigInt count = ec.getBalanceAt(ctx,as.get(0).getAddress(),-1);
//                tx.append("getBalanceAt count : "+ count + "\n");
//                Log.d("111", "TestFunction balance : " + count);
//                long lbknumber = ec.getBlockByNumber(ctx,-1).getNumber();
//                Log.d("111", "TestFunction lbknumber : " + lbknumber);
//
//            }catch (Exception e)
//            {
//                e.printStackTrace();
//            }
        }

        try {
            EthereumClient ec = node.getEthereumClient();
            if(ec.syncProgress(ctx) !=null)
            {
                long highestblock = ec.syncProgress(ctx).getHighestBlock();
                Log.d("111", "TestFunction highestblock : " + highestblock);
                long currentblock = ec.syncProgress(ctx).getCurrentBlock();
                Log.d("111", "TestFunction currentblock : " + currentblock);

            }
            long lbknumber = ec.getBlockByNumber(ctx,-1).getNumber();
            Log.d("111", "TestFunction lbknumber : " + lbknumber);

           long header =  ec.getHeaderByNumber(ctx,-1).getNumber();
            Log.d("111", "TestFunction header : " + header);

//            BigInt difficulty =  ec.getHeaderByNumber(ctx,-1).getDifficulty();
//            Log.d("111", "TestFunction difficulty : " + difficulty);
//
//            String peerInfoString = node.getPeersInfo().toString();
//            Log.d("111", "TestFunction peerInfoString : " + peerInfoString);

//            if(node.getEthereumClient().getHeaderByNumber(ctx,69) !=null )
//            {
//                long num = node.getEthereumClient().getHeaderByNumber(ctx,69).getNumber();
//                Log.d("111", "TestFunction num : " + num);
//                String jsonstring = node.getEthereumClient().getHeaderByNumber(ctx,69).encodeJSON();
//                Log.d("111", "TestFunction jsonstring : " + jsonstring);
//            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    void TestContract()
    {
        //Geth.deployContract()
//        try {
//            EthereumClient ec = node.getEthereumClient();
//
//            KeyStore ks = new KeyStore(RootDir,Geth.LightScryptN,Geth.LightScryptP);
////            Account act = ks.newAccount(passwd);
////            Log.d("111","contract address :" +  act.getAddress().getHex());
////            Context ctx = Geth.newContext();
////            Transaction tx = null;
////            tx = Geth.newTransaction(ec.getNonceAt(ctx, act.getAddress(), -1)/*nonce*/,
////                    new Address("0x99c776c0802b4d3f801d7d611a17fe4321e61d60")/*to your address*/,
////                    new BigInt(10000000)/*ether*/,
////                    4300000/*gas limit*/,
////                    new BigInt(300000)/*gas price*/,
////                    null);
////            Transaction signedtx = null;
////            signedtx = ks.signTxPassphrase(act, "123456", tx, new BigInt(2265)/*Your network ID*/);
////            ec.sendTransaction(ctx,signedtx);
////
////            Receipt rec = null;
////            rec = ec.getTransactionReceipt(ctx, signedtx.gHash());
//            Log.d("111","first account is " + ks.getAccounts().get(0).getAddress().getHex());
//            Account ac = ks.getAccounts().get(0);
//
//            TransactOpts tops = new TransactOpts();
//            tops.setContext(ctx);
//            tops.setFrom(ac.getAddress());
//            tops.setGasLimit(9);
//            tops.setGasPrice(new BigInt(30000));
//            tops.setSigner(new MySigner(ac,ks,"Creation password", new BigInt(15)));//Refer MySigner.java
//            tops.setNonce(ec.getNonceAt(ctx, ac.getAddress(), -1));
//
//            String bytecode = "6060604052341561000c57fe5b5b60b38061001b6000396000f300606060405263ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416631003e2d2811460435780636d4ce63c146055575bfe5b3415604a57fe5b60536004356074565b005b3415605c57fe5b60626080565b60408051918252519081900360200190f35b60008054820190555b50565b6000545b905600a165627a7a72305820cea55ffbb44b744ad40c6f202f52d1fcd2d8cc0a1cf29b6b3f93e6a4b1b0f3120029";
//            String abi = "[{\"constant\":true,\"inputs\":[],\"name\":\"say\",\"outputs\":[{\"name\": \"\",\"type\": \"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"get\",\"outputs\":[{\"name\":\"c\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"}]";
////            String bytecode = "6060604052341561000f57600080fd5b6040516102b83803806102b8833981016040528080518201919050508060009080519060200190610041929190610048565b50506100ed565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061008957805160ff19168380011785556100b7565b828001600101855582156100b7579182015b828111156100b657825182559160200191906001019061009b565b5b5090506100c491906100c8565b5090565b6100ea91905b808211156100e65760008160009055506001016100ce565b5090565b90565b6101bc806100fc6000396000f300606060405260043610610041576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063954ab4b214610046575b600080fd5b341561005157600080fd5b6100596100d4565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561009957808201518184015260208101905061007e565b50505050905090810190601f1680156100c65780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b6100dc61017c565b60008054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156101725780601f1061014757610100808354040283529160200191610172565b820191906000526020600020905b81548152906001019060200180831161015557829003601f168201915b5050505050905090565b6020604051908101604052806000815250905600a165627a7a72305820721c434ad13b1877c183c3b442293883578a0ab3ce105b6156604b5d5d221bd00029";
////            String abi = "[{\"constant\":true,\"inputs\":[],\"name\":\"say\",\"outputs\":[{\"name\":\"\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"name\":\"_greeting\",\"type\":\"string\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"}]";
//            BoundContract bc = Geth.deployContract(tops, abi, hexStringToByteArray(bytecode), ec, new Interfaces(0));
//
//
//            Log.d("111","contract Address is " + bc.getAddress().getHex());
//
//            //Geth.
//
//        }catch (Exception e)
//        {
//                Log.d("111",e.getMessage().toString());
//        }
    }
    //调用android合约接口
    void TestGetContract()
    {
        String AbiString = "[ { \"constant\": true, \"inputs\": [], \"name\": \"name\", \"outputs\": [ { \"name\": \"\", \"type\": \"string\", \"value\": \"CoinFuns\" } ], \"payable\": false, \"type\": \"function\" }, { \"constant\": false, \"inputs\": [ { \"name\": \"_spender\", \"type\": \"address\" }, { \"name\": \"_value\", \"type\": \"uint256\" } ], \"name\": \"approve\", \"outputs\": [ { \"name\": \"success\", \"type\": \"bool\" } ], \"payable\": false, \"type\": \"function\" }, { \"constant\": true, \"inputs\": [], \"name\": \"totalSupply\", \"outputs\": [ { \"name\": \"totalSupply\", \"type\": \"uint256\", \"value\": \"30000000000000000000\" } ], \"payable\": false, \"type\": \"function\" }, { \"constant\": false, \"inputs\": [ { \"name\": \"_from\", \"type\": \"address\" }, { \"name\": \"_to\", \"type\": \"address\" }, { \"name\": \"_value\", \"type\": \"uint256\" } ], \"name\": \"transferFrom\", \"outputs\": [ { \"name\": \"success\", \"type\": \"bool\" } ], \"payable\": false, \"type\": \"function\" }, { \"constant\": true, \"inputs\": [], \"name\": \"INITIAL_SUPPLY\", \"outputs\": [ { \"name\": \"\", \"type\": \"uint256\", \"value\": \"30000000000000000000\" } ], \"payable\": false, \"type\": \"function\" }, { \"constant\": true, \"inputs\": [], \"name\": \"decimals\", \"outputs\": [ { \"name\": \"\", \"type\": \"uint256\", \"value\": \"10\" } ], \"payable\": false, \"type\": \"function\" }, { \"constant\": true, \"inputs\": [], \"name\": \"_totalSupply\", \"outputs\": [ { \"name\": \"\", \"type\": \"uint256\", \"value\": \"30000000000000000000\" } ], \"payable\": false, \"type\": \"function\" }, { \"constant\": true, \"inputs\": [ { \"name\": \"_owner\", \"type\": \"address\" } ], \"name\": \"balanceOf\", \"outputs\": [ { \"name\": \"balance\", \"type\": \"uint256\", \"value\": \"0\" } ], \"payable\": false, \"type\": \"function\" }, { \"constant\": false, \"inputs\": [], \"name\": \"destroy\", \"outputs\": [], \"payable\": false, \"type\": \"function\" }, { \"constant\": true, \"inputs\": [], \"name\": \"symbol\", \"outputs\": [ { \"name\": \"\", \"type\": \"string\", \"value\": \"CFS\" } ], \"payable\": false, \"type\": \"function\" }, { \"constant\": false, \"inputs\": [], \"name\": \"freezeAll\", \"outputs\": [], \"payable\": false, \"type\": \"function\" }, { \"constant\": false, \"inputs\": [ { \"name\": \"_to\", \"type\": \"address\" }, { \"name\": \"_value\", \"type\": \"uint256\" } ], \"name\": \"transfer\", \"outputs\": [ { \"name\": \"success\", \"type\": \"bool\" } ], \"payable\": false, \"type\": \"function\" }, { \"constant\": true, \"inputs\": [], \"name\": \"_creator\", \"outputs\": [ { \"name\": \"\", \"type\": \"address\", \"value\": \"0x38aa4aebc6d39adf885c20c678feeea16504d578\" } ], \"payable\": false, \"type\": \"function\" }, { \"constant\": true, \"inputs\": [ { \"name\": \"_owner\", \"type\": \"address\" }, { \"name\": \"_spender\", \"type\": \"address\" } ], \"name\": \"allowance\", \"outputs\": [ { \"name\": \"remaining\", \"type\": \"uint256\", \"value\": \"0\" } ], \"payable\": false, \"type\": \"function\" }, { \"inputs\": [], \"payable\": false, \"type\": \"constructor\" }, { \"anonymous\": false, \"inputs\": [ { \"indexed\": true, \"name\": \"from\", \"type\": \"address\" }, { \"indexed\": true, \"name\": \"to\", \"type\": \"address\" }, { \"indexed\": false, \"name\": \"value\", \"type\": \"uint256\" } ], \"name\": \"Transfer\", \"type\": \"event\" }, { \"anonymous\": false, \"inputs\": [ { \"indexed\": true, \"name\": \"owner\", \"type\": \"address\" }, { \"indexed\": true, \"name\": \"spender\", \"type\": \"address\" }, { \"indexed\": false, \"name\": \"value\", \"type\": \"uint256\" } ], \"name\": \"Approval\", \"type\": \"event\" } ]";
        EthereumClient ec  = null;
        try{
            ec = node.getEthereumClient();
            BoundContract bContract = Geth.bindContract(new Address("0x090Cc6e0340086850b9B16be3BAA8C64c3FDAFa0"),AbiString,ec);
            Interfaces results = Geth.newInterfaces(1);
            Interface result = Geth.newInterface();
            result.setDefaultBigInt();
            results.set(0, result);
            Interfaces params = Geth.newInterfaces(1);
            Interface anInterface = Geth.newInterface();
            anInterface.setAddress(new Address("0x648EF104C8453702b89abAa2E1822Ae38cD6DD80"));
            params.set(0,anInterface);
            CallOpts opts = Geth.newCallOpts();
            opts.setContext(new Context());
            bContract.call(opts,results,"balanceOf",params);
            BigInt balance = results.get(0).getBigInt();
            Log.d("111","Test Get Contract" + balance);
        }catch (Exception e)
        {

        }
//        try {
//            KeyStore ks = new KeyStore(RootDir,Geth.LightScryptN,Geth.LightScryptP);
//            Log.d("111","first  TestGetContract account is " + ks.getAccounts().get(0).getAddress().getHex());


//            Account ac = ks.getAccounts().get(0);
////        Geth.newCallOpts();
//        CallMsg callMsg = Geth.newCallMsg();
//        callMsg.setFrom(ac.getAddress());
//        callMsg.setTo(new Address("0x090Cc6e0340086850b9B16be3BAA8C64c3FDAFa0".toLowerCase()));//This is the contract address
//        callMsg.setData(hexStringToByteArray("70a08231"));
//
//        byte[] ret = null;
//        ret = ec.callContract(ctx, callMsg, -1);
//
//        Log.e("ret is ", ret.toString());
//        Log.e("ret is ", bytesToHex(ret));
//        }catch (Exception e)
//        {
//            Log.e("ret is ", e.getMessage().toString());
//        }
    }
    public BigInt getBalanceAt(String addressString)
    {
        BigInt bBalance = new BigInt(0);
        try {
            Address address = new Address(addressString);
            bBalance = node.getEthereumClient().getBalanceAt(new Context(), address, -1);
        }catch (Exception e)
        {
            return bBalance;
        }
        return bBalance;
    }
    private final static char[] hexArray = "0123456789abcdef".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
    public final static byte[] hexStringToByteArray(String s) {
        byte[] b = new byte[s.length() / 2];
        for (int i = 0; i < b.length; i++) {
            int index = i * 2;
            int v = Integer.parseInt(s.substring(index, index + 2), 16);
            b[i] = (byte) v;
        }
        return b;
    }
}
