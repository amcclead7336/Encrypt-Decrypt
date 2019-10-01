public interface Encrypt {
    String encrypt(String message, int key);
    String decrypt(String message, int key);
}

class MoveLetterAlgEncrypt implements Encrypt {

    final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public String encrypt(String message, int key){

        String result = "";
        char[] messageArray = message.toLowerCase().toCharArray();

        for (char letter : messageArray){

            if (ALPHABET.indexOf(letter)>=0) {

                int move = ((ALPHABET.indexOf(letter) + key ) % ALPHABET.length());

                if (move < 0) {
                    move = (ALPHABET.length()-1)+move;

                }
                result += ALPHABET.charAt(move);
            }
            else {
                result += letter;
            }
        }
        return result;
    }

    @Override
    public String decrypt(String message, int key){
        String result = "";
        char[] messageChar =  message.toLowerCase().toCharArray();
        for (char letter : messageChar){
            int pos = ALPHABET.indexOf(letter);
            int move = (pos - key) % ALPHABET.length();
            if (move<0){
                move = ALPHABET.length()+move;
            }
            result+= (ALPHABET.charAt(move));
        }
        return result;

    }
}

class BitShiftAlgEncrypt implements Encrypt{

    public String encrypt(String message, int key){

        String response = "";

        char[] messageArray = message.toCharArray();

        for (char letter : messageArray){
            letter += key;

            response += letter;
        }

        return response;
    }

    @Override
    public String decrypt(String message, int key){
        return encrypt(message, key*-1);
    }
}
