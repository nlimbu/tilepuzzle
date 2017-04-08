package primecode.net.eleventiles;

/**
 * @author nagendralimbu
 */
public class Main {

    Main() {
    }

    public static void main(String[] args) {
        String[] allConfigs = { 
                "ac++dad+d_bd+dbb2ac++dad+db_b+dbd",
        //        "ba++_dd+dbcd+dab2ad++b_a+dcdb+dbd",
        //        "ca++bdd+b_dd+adb2dc++aab+bddd+bd_",
        //        "cb++bdd+bdad+a_d2bd++_cb+ddad+dba",
        //        "dc++ddd+bb_a+bda2dc++ddd+bbba+ad_",
        //        "dd++ba_+bdcd+dba2bd++_ba+ddcb+dad",
        //        "dd++ddd+c_ab+abb2dd++ddd+cb_a+abb",
        //        "dd++cbd+dbd_+aab2db++dbb+cd_a+dda",
        //         "dd++ba_+cdba+dbd2dd++baa+dd_b+cbd",
        //---------------------------------------------            
        //        "dd++bbc+bdda+d_a2bd++dcd+_bad+bda",
        //        "b_++ddb+daab+dcd2bd++_ba+ddab+dcd",
        //--------------------------------------------
        //        "dc++d_b+ddda+bba2_d++bcd+bdda+bda",
        //        "dd++dbb+d_db+aac2dd++bdd+dacb+a_b",
        //        "ba++dba+d_bd+ddc2ad++bba+db_d+ddc",
        //        "dd++dad+ba_b+dcb2db++d_d+aadc+dbb",
        //        "cb++a_a+dbdd+dbd2cb++aba+dd_d+bdd"
        };

        for (String config : allConfigs) {
            ElevenTile elevenTile = new ElevenTile(config.split("2"));
            elevenTile.configureTiles();
        }
    }
}
