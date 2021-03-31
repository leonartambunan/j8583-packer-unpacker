package id.co.nio.iso8583;

import com.solab.iso8583.IsoMessage;
import com.solab.iso8583.IsoValue;
import com.solab.iso8583.MessageFactory;
import com.solab.iso8583.parse.ConfigParser;

import java.io.IOException;
import java.text.ParseException;

public class Unpacker {

    public static void main(String[] args) throws IOException, ParseException {
        MessageFactory<IsoMessage> mf = new MessageFactory();
        ConfigParser.configureFromClasspathConfig(mf, "iso8583-def.xml");

        String message = "0430F23A00098A80800A0000004000000000162008899999999999491000000000100089033116060800071016060803310331800000000039050390520000000332600AAAABBB 3600000070199999020000071003311606080000000090500000000905";
        IsoMessage m = mf.parseMessage(message.getBytes(), 0);
        if (m != null) {
            System.out.printf("Message type: %04x%n", m.getType());
            System.out.println("FIELD TYPE    VALUE");

            for(int i = 2; i <= 128; ++i) {
                IsoValue<?> f = m.getField(i);
                if (f != null) {
                    System.out.printf("%5d %-7s (%4d) [", i, f.getType(), f.getLength());
                    System.out.print(f.toString());
                    System.out.println(']');
                }
            }
        }
    }
}
