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
        ConfigParser.configureFromClasspathConfig(mf, "iso8583-def.xml");

        IsoMessage m = mf.newMessage(0x0200);
        m.setValue(3, "000000", IsoType.NUMERIC, 6);
        m.setValue(18, "0001", IsoType.NUMERIC, 4);
        m.setValue(41, "3239313130303031", IsoType.ALPHA, 8);
        m.setValue(60, "001054455354204D45535347", IsoType.LLVAR,24);
        m.setValue(63, "0301", IsoType.LLLVAR, 4);

        m.setForceSecondaryBitmap(false);

        System.out.println(m.debugString());

    }
}
