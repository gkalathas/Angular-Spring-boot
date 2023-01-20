package com.ots.trainingapi.global.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TextUtils {

  private static final Logger logger = LogManager.getLogger(TextUtils.class);

  /**
   * Μετατροπή του δοθέντος αλφαριθμητικού σε κεφαλαία. Η μέθοδος φροντίζει για
   * την αφαίρεση των τόνων από τα ελληνικά φωνήεντα.
   *
   * @param text - Το αλφαριθμητικό προς μετατροπή
   * @return Το αλφαριθμητικό με κεφαλαία γράμματα και χωρίς τόνους
   */
  public static String toUpperCaseGreekSupport(String text) {
    if (text == null) {
      return null;
    } else {
      text = text.toUpperCase();

      text = text.replace("Ά", "Α");
      text = text.replace("Έ", "Ε");
      text = text.replace("Ή", "Η");
      text = text.replace("Ί", "Ι");
      text = text.replace("Ό", "Ο");
      text = text.replace("Ύ", "Υ");
      text = text.replace("Ώ", "Ω");

      return text;
    }
  }

  /**
   * Μετατροπή του δοθέντος αλφαριθμητικού σε κεφαλαία αφαιρώντας σημεία στίξης.
   * Η μέθοδος φροντίζει για την αφαίρεση των τόνων και των διαλυτικών από τα
   * ελληνικά φωνήεντα.
   *
   * @param text - Το αλφαριθμητικό προς μετατροπή
   * @return Το αλφαριθμητικό με κεφαλαία γράμματα και χωρίς τόνους και
   * διαλυτικά
   */
  public static String toUpperCaseGreekSupportExtended(String text) {
    return toUpperCaseGreekSupportExtended(text, false);
  }

  public static String toUpperCaseGreekSupportExtended(String text, boolean removeApostrophes) {
    if (text == null) {
      return null;
    } else {

      text = text.replace("ΐ", "ϊ");
      text = text.replace("ΰ", "ϋ");

      text = text.toUpperCase();

      text = text.replace("Ά", "Α");
      text = text.replace("Έ", "Ε");
      text = text.replace("Ή", "Η");
      text = text.replace("Ί", "Ι");
      text = text.replace("Ϊ", "Ι");
      text = text.replace("Ό", "Ο");
      text = text.replace("Ύ", "Υ");
      text = text.replace("Ϋ", "Υ");
      text = text.replace("Ώ", "Ω");

      //Αντικατάσταση αποστρόφων
      if (removeApostrophes) {
        text = text.replace("'", "");
        text = text.replace("`", "");
        text = text.replace("΄", "");
      }

      return text;
    }
  }

  /**
   * Concatenation των στοιχείων μιας λίστας από strings σε ένα string. Τα
   * στοιχεία διαχωρίζονται με το δοθέν διαχωριστικό.
   */
  public static String concatListToString(List<String> list, String separator) {

    if (list == null || list.isEmpty()) {
      return null;
    }

    String output = "";

    for (String element : list) {
      output += element + separator;
    }

    output = output.substring(0, output.length() - separator.length());

    return output;
  }

  /**
   * Παραγωγή λίστας με τιμές Long από ένα string αριθμών διαχωρισμένων με
   * κόμμα.
   */
  public static List<Long> createListOfLongFromCommaSeparatedString(String input) {

    //Η λίστα που θα επιστραφεί
    List<Long> list = new ArrayList<Long>();

    if (input == null || input.isEmpty()) {
      return list;
    }

    input = input.replaceAll(" ", "");

    //Διαχωρισμός του string
    String[] splitResults = input.split(",");

    try {
      for (String splitResult : splitResults) {
        //Μετατροπή του υποstring σε long και προσθήκη στη λίστα
        Long value = Long.valueOf(splitResult);
        list.add(value);
      }
    } catch (NumberFormatException e) {
      System.err.println(e.getMessage());
      logger.error(e.getMessage());
    }

    return list;
  }

  /**
   * Έλεγχος αν δύο strings είναι όμοια. Λαμβάνονται υπόψη και οι null τιμές. Αν
   * είναι και τα δύο null, τότε θεωρούνται όμοια. Επίσης λαμβάνονται υπόψη τα
   * κενά strings και οι boolean τιμές false.
   */
  public static Boolean stringsAreEqual(String s1, String s2) {

    if (s1 != null && (s1.isEmpty() || s1.equals("false"))) {
      s1 = null;
    }
    if (s2 != null && (s2.isEmpty() || s2.equals("false"))) {
      s2 = null;
    }

    if (s1 == null && s2 == null) {
      return true;
    } else if (s1 == null && s2 != null) {
      return false;
    } else if (s1 != null && s2 == null) {
      return false;
    } else {
      return s1.equals(s2);
    }
  }

  /**
   * Έλεγχος αν δύο strings είναι όμοια ή το πρώτο περιέχει το δεύτερο.
   * Λαμβάνονται υπόψη και οι null τιμές. Αν είναι και τα δύο null, τότε
   * θεωρούνται όμοια. Επίσης λαμβάνονται υπόψη τα κενά strings και οι boolean
   * τιμές false.
   */
  public static Boolean equalsOrContains(String s1, String s2) {

    if (s1 != null && (s1.isEmpty() || s1.equals("false"))) {
      s1 = null;
    }
    if (s2 != null && (s2.isEmpty() || s2.equals("false"))) {
      s2 = null;
    }

    if (s1 == null && s2 == null) {
      return true;
    } else if (s1 == null && s2 != null) {
      return false;
    } else if (s1 != null && s2 == null) {
      return false;
    } else if (s1.equals(s2)) {
      return true;
    } else {
      return s1.equals(s2) || s1.contains(s2);
    }
  }

  /**
   * Έλεγχος αν ένα string είναι null ή κενό
   */
  public static Boolean stringIsBlank(String s) {
    return s == null || s.isEmpty();
  }

  /**
   * Έλεγχος εάν ένα string είναι null ή κενό (trimmed) Πρόκειται για τη
   * StringUtils.isEmpty() συν τον έλεγχο του trim()
   *
   * @param s
   * @return
   */
  public static Boolean isEmpty(String s) {
    return s == null || s.length() == 0 || s.trim().length() == 0;
  }

  /**
   *
   */
  public static String nl2space(String text) {
    return text != null ? text.replace("\r\n", " ").replace("\n", " ").replace("\r", " ") : null;
  }

  public static String getPersonFullName(String firstName, String lastName, boolean startWithLastName) {

    String fullName = "";

    if (startWithLastName) {
      fullName = (StringUtils.isEmpty(lastName) ? "" : lastName)
              + (StringUtils.isEmpty(firstName) ? "" : " " + firstName);
    } else {
      fullName = (StringUtils.isEmpty(firstName) ? "" : firstName)
              + (StringUtils.isEmpty(lastName) ? "" : " " + lastName);
    }

    fullName = StringUtils.trim(fullName);

    return fullName;
  }

  public static String truncate(String value, int length) {
    if (value != null && value.length() > length) {
      return value.substring(0, length - 5) + "...";
    } else {
      return value;
    }
  }

  /**
   * Κανονικοποίηση (μετατροπή) αλφαριθμητικού πεδίου τύπου ΤΚ Αφαίρεση κάποιων
   * χαρακτήρων [" ", "-", "/"]
   *
   * @param text
   * @return
   */
  public static String normalizeTk(String text) {
    if (text == null) {
      return null;
    } else {
      text = text.replace(" ", "");
      text = text.replace("-", "");
      text = text.replace("/", "");

      return text;
    }
  }

  /**
   * Αν το δεδομένο string είναι null ή κενό, τότε επιστρέφεται br tag. Αλλιώς,
   * το string επιστρέφεται όπως είναι.
   */
  public static String normalizeGridString(String s, boolean forExcel, boolean first) {
    if (s == null || isEmpty(s)) {
      return forExcel || first ? "" : "<br/>";
    } else {
      return s;
    }
  }

  /**
   * Αν το δεδομένο string είναι null, επιστροφή ενός κενού string, αλλιώς
   * επιστροφή του εαυτού του
   */
  public static String nullToEmpty(String s) {
    return (s == null) ? "" : s;
  }

  /**
   * Μετατροπή των ελληνικών χαρακτήρων του δοθέντος αλφαριθμητικού σε μοτίβο
   * regex. Η μέθοδος φροντίζει μόνο για τους ελληνικούς χαρακτήρες μαζί με τα
   * σύμβολα _ και %.
   *
   * @param text - Το αλφαριθμητικό προς μετατροπή
   * @return Το αλφαριθμητικό με τους ελληνικούς χαρακτήρες σε μοτίβο regex
   */
  public static String changeGreekCharactersToRegex(String text) {
    String regex = "";
    if (text == null) {
      return null;
    } else {
      //Μετατροπή του δοθέντος αλφαριθμητικού ώστε να ελεγχθούν λιγότεροι χαρακτήρες
      String textUpperCase = toUpperCaseGreekSupportExtended(text);
      textUpperCase = changeLatinCharactersToRegex(textUpperCase);

      for (int i = 0, textUpperCaseSize = textUpperCase.length(); i < textUpperCaseSize; i++) {
        char character = textUpperCase.charAt(i);

        switch (character) {
          case 'Α':
            regex = regex.concat("[άαΆΑ]");
            break;
          case 'Β':
            regex = regex.concat("[βΒ]");
            break;
          case 'Γ':
            regex = regex.concat("[γΓ]");
            break;
          case 'Δ':
            regex = regex.concat("[δΔ]");
            break;
          case 'Ε':
            regex = regex.concat("[έεΈΕ]");
            break;
          case 'Ζ':
            regex = regex.concat("[ζΖ]");
            break;
          case 'Η':
            regex = regex.concat("[ήηΉΗ]");
            break;
          case 'Θ':
            regex = regex.concat("[θΘ]");
            break;
          case 'Ι':
            regex = regex.concat("[ϊίΐιΊΪΙ]");
            break;
          case 'Κ':
            regex = regex.concat("[κΚ]");
            break;
          case 'Λ':
            regex = regex.concat("[λΛ]");
            break;
          case 'Μ':
            regex = regex.concat("[μΜ]");
            break;
          case 'Ν':
            regex = regex.concat("[νΝ]");
            break;
          case 'Ξ':
            regex = regex.concat("[ξΞ]");
            break;
          case 'Ο':
            regex = regex.concat("[όοΌΟ]");
            break;
          case 'Π':
            regex = regex.concat("[πΠ]");
            break;
          case 'Ρ':
            regex = regex.concat("[ρΡ]");
            break;
          case 'Σ':
            regex = regex.concat("[ςσΣ]");
            break;
          case 'Τ':
            regex = regex.concat("[τΤ]");
            break;
          case 'Υ':
            regex = regex.concat("[ύϋΰυΎΫΥ]");
            break;
          case 'Φ':
            regex = regex.concat("[φΦ]");
            break;
          case 'Χ':
            regex = regex.concat("[χΧ]");
            break;
          case 'Ψ':
            regex = regex.concat("[ψΨ]");
            break;
          case 'Ω':
            regex = regex.concat("[ώωΏΩ]");
            break;
          case '_':
            regex = regex.concat(".");
            break;
          case '%':
            regex = regex.concat(".*");
            break;
          default:
            regex = regex.concat(String.valueOf(character));
          //TODO: add support for English ;
        }
      }

    }

    return regex;
  }

  public static String changeLatinCharactersToRegex(String text) {
    String regex = "";
    if (text == null) {
      return null;
    } else {

      for (int i = 0, textSize = text.length(); i < textSize; i++) {
        char character = text.charAt(i);

        switch (character) {
          case 'A':
            regex = regex.concat("[Aa]");
            break;
          case 'B':
            regex = regex.concat("[Bb]");
            break;
          case 'C':
            regex = regex.concat("[Cc]");
            break;
          case 'D':
            regex = regex.concat("[Dd]");
            break;
          case 'E':
            regex = regex.concat("[Ee]");
            break;
          case 'F':
            regex = regex.concat("[Ff]");
            break;
          case 'G':
            regex = regex.concat("[Gg]");
            break;
          case 'H':
            regex = regex.concat("[Hh]");
            break;
          case 'I':
            regex = regex.concat("[Ii]");
            break;
          case 'J':
            regex = regex.concat("[Jj]");
            break;
          case 'K':
            regex = regex.concat("[Kk]");
            break;
          case 'L':
            regex = regex.concat("[Ll]");
            break;
          case 'M':
            regex = regex.concat("[Mm]");
            break;
          case 'N':
            regex = regex.concat("[Nn]");
            break;
          case 'O':
            regex = regex.concat("[Oo]");
            break;
          case 'P':
            regex = regex.concat("[Pp]");
            break;
          case 'Q':
            regex = regex.concat("[Qq]");
            break;
          case 'R':
            regex = regex.concat("[Rr]");
            break;
          case 'S':
            regex = regex.concat("[Ss]");
            break;
          case 'T':
            regex = regex.concat("[Tt]");
            break;
          case 'U':
            regex = regex.concat("[Uu]");
            break;
          case 'V':
            regex = regex.concat("[Vv]");
            break;
          case 'W':
            regex = regex.concat("[Ww]");
            break;
          case 'X':
            regex = regex.concat("[Xx]");
            break;
          case 'Y':
            regex = regex.concat("[Yy]");
            break;
          case 'Z':
            regex = regex.concat("[Zz]");
            break;
          default:
            regex = regex.concat(String.valueOf(character));
          //TODO: add support for English ;
        }
      }

    }

    return regex;
  }
  
  public static String generateID(){
    return UUID.randomUUID().toString();
  }  

}
