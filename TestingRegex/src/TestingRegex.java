import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestingRegex {
    public static void main(String[] args) {

        Pattern regexp = Pattern.compile("^(\\d{1,3}\\.){3}\\d{1,3}");
        Matcher matcher = regexp.matcher("612.57.72.653   03/Jun/2012:09:12:23 -0500  03 Jun\\t06\\n2012_09-12 23  -0500   $   GET\\r/product/product");
        while(matcher.find()) {
            System.out.println(matcher.group());
        }

        String input = "612.57.72.653   03/Jun/2012:09:12:23 -0500   03 Jun\t06\n2012_09-12 23  -0500   $   GET\r/product/product2       200     0       /product/product2       Mozilla/4.0 (comp)";
        for(String letter : input.split("")){
            System.out.println(letter);
        }
    }
}