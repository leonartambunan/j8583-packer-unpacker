package id.co.nio.iso8583;

import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.IsoType;
import com.solab.iso8583.MessageFactory;
import com.solab.iso8583.parse.ConfigParser;

import java.io.IOException;
import java.text.ParseException;

public class Packer {

    public static void main(String[] args) throws IOException, ParseException {
        MessageFactory<IsoMessage> mf = new MessageFactory();
        ConfigParser.configureFromClasspathConfig(mf,"config_bank.xml");

        IsoMessage m = mf.newMessage(0200);
        m.setValue(3, "000000", IsoType.ALPHA, 6);
        m.setValue(11, "000001", IsoType.ALPHA, 6);
        m.setValue(41, "3239313130303031", IsoType.ALPHA, 16);
        m.setValue(60, "001054455354204D45535347", IsoType.ALPHA, 24);
        m.setValue(70, "0301", IsoType.ALPHA, 4);
        m.setForceSecondaryBitmap(true);

        System.out.println(m.debugString());

    }
}
