import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);
        String mode = "enc";
        String data = null;
        String inFilename = null;
        String outFilename = null;
        String algorithm = "shift";
        String response = "";
        Encrypt encryptAlg = null;

        int key = -1;

        for (int i = 0, j = 1; j < args.length; i += 2, j += 2) {
            String arg = args[i];
            String option = args[j];


            if ("-in".equals(arg)) {
                inFilename = option;
            }
            if ("-out".equals(arg)) {
                outFilename = option;
            }
            if ("-mode".equals(arg)) {
                mode = option;
            }
            if ("-data".equals(arg)) {
                data = option;
            }
            if ("-key".equals(arg)) {
                key = Integer.parseInt(option);
            }
            if ("-alg".equals(arg)) {
                algorithm = option;
            }
        }

        if (inFilename != null) {
            data = "";
            Scanner fileScan = new Scanner(new File(inFilename));
            while (fileScan.hasNextLine()) {
                data += fileScan.nextLine();
            }
            fileScan.close();
        }

        if (data == null) {
            data = scan.nextLine();
        }

        if (key == -1) {
            key = scan.nextInt();
        }

        if ("shift".equals(algorithm)) {
            encryptAlg = new MoveLetterAlgEncrypt();
        }

        if ("unicode".equals(algorithm)) {
            encryptAlg = new BitShiftAlgEncrypt();
        }

        if ("enc".equals(mode)) {
            response = encryptAlg.encrypt(data, key);
        } else if (mode.equals("dec")) {
            response = encryptAlg.decrypt(data, (key));
        }
        if (outFilename == null) {
            System.out.println(response);
        } else {
            PrintWriter writer = new PrintWriter(new File(outFilename));
            writer.println(response);
            writer.close();
        }

    }
}