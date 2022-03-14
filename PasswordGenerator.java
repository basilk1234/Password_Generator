import java.util.Random;
public class PasswordGenerator {

	public static void main(String[] args) {

		final String[][][] book = {
				{
					{"ALICE was beginning to get very tired of sitting by her sister on the\n",
						"bank, and of having nothing to do. Once or twice she had peeped into the\n",
						"book her sister was reading, but it had no pictures or conversations in\n",
						"it, \"and what is the use of a book,\" thought Alice, \"without pictures or",
					"conversations?\"\n"},

					{"So she was considering in her OWN mind (as well as she could, for the\n",
						"day made her feel very sleepy and stupid), whether the pleasure of\n",
						"making a daisy-chain would be worth the trouble of getting up and\n",
						"picking the daisies, when suddenly a White Rabbit with pink eyes ran\n",
					"close by her.\n"},

					{"There was nothing so very remarkable in that, nor did Alice think it so\n",
						"very much out of the way to hear the Rabbit say to itself, \"Oh dear! Oh\n",
						"dear! I shall be too late!\" But when the Rabbit actually took a watch\n",
						"out of its waistcoat-pocket and looked at it and then hurried on, Alice\n",
						"started to her feet, for it flashed across her mind that she had never\n",
						"before seen a rabbit with either a waistcoat-pocket, or a watch to take\n",
						"out of it, and, burning with curiosity, she ran across the field after\n",
						"it and was just in time to see it pop down a large rabbit-hole, under\n",
					"the hedge. In another moment, down went Alice after it!"}
				},
				{
					{"\"WHAT a curious feeling!\" said Alice. \"I must be shutting up like a\n",
					"telescope!\"\n"},

					{"And so it was indeed! She was now only ten inches high, and her face\n",
						"brightened up at the thought that she was now the right size for going\n",
					"through the little door into that lovely garden.\n"},

					{"After awhile, finding that nothing more happened, she decided on going\n",
						"into the garden at once; but, alas for poor Alice! When she got to the\n",
						"door, she found she had forgotten the little golden key, and when she\n",
						"went back to the table for it, she found she could not possibly reach\n",
						"it: she could see it quite plainly through the glass and she tried her\n",
						"best to climb up one of the legs of the table, but it was too slippery,\n",
						"and when she had tired herself out with trying, the poor little thing\n",
					"sat down and cried.\n"},

					{"\"Come, there's no use in crying like that!\" said Alice to herself rather\n",
						"sharply. \"I advise you to leave off this minute!\" She generally gave\n",
						"herself very good advice (though she very seldom followed it), and\n",
						"sometimes she scolded herself so severely as to bring tears into her\n",
					"eyes.\n"},

					{"Soon her eye fell on a little glass box that was lying under the table:\n",
						"she opened it and found in it a very small cake, on which the words \"EAT\n",
						"ME\" were beautifully marked in currants. \"Well, I'll eat it,\" said\n",
						"Alice, \"and if it makes me grow larger, I CAN reach the key; and if it\n",
						"makes me grow smaller, I can creep under the door: so either way I'll\n",
					"get into the garden, and I don't care which happens!\"\n"},

					{"She ate a little bit and said anxiously to herself, \"Which way? Which\n",
						"way?\" holding her hand on the top of her head to feel which way she was\n",
						"growing; and she was quite surprised to find that she remained the same\n",
					"size. So she set to work and very soon finished off the cake."}
				},
				{
					{"The King and Queen of Hearts were seated on their throne when they\n",
						"arrived, with a great crowd assembled about them--all sorts of little\n",
						"birds and beasts, as well as the whole pack of cards: the Knave was\n",
						"standing before them, in chains, with a soldier on each side to guard\n",
						"him; and near the King was the White Rabbit, with a trumpet in one hand\n",
						"and a scroll of parchment in the other. In the very middle of the court\n",
						"was a table, with a large DISH of tarts upon it. \"I wish they'd get the\n",
					"trial done,\" Alice thought, \"and hand 'round the refreshments!\"\n"},

					{"The judge, by the way, was the King and he wore his crown over his great\n",
						"wig. \"That's the jury-box,\" thought Alice; \"and those twelve creatures\n",
					"(some were animals and some were birds) I suppose they are the jurors.\"\n"},

					{"Just then the White Rabbit cried out \"Silence in the court!\"\n"},

					{"\"HERALD! read the accusation!\" said the King."}
				}
		};

		// You need to input a "seed" value - this is the value used to generate random values.
		// We typically use the current system time in milliseconds, because it is a huge value,
		// and it differs every time we run the program - this ensures we have the most random possible values.
		Random rand = new Random(System.currentTimeMillis());
		final int NUM_WORDS_PER_PASSWORD = 3;
		final int MAX_NUM_PASSWORDS = 10000;
		final int MIN_PASSWORD_LENGTH = 8;
		final int MAX_PASSWORD_LENGTH = 16;
		final int NUM_SPECIAL_CHARS = 1;

		int numPasswords = 0;
		for (; numPasswords < MAX_NUM_PASSWORDS; numPasswords++)
		{
			String password = "";
			boolean passwordValid = false;

			// These varibles here are used to keep track of the number of failed attempts of each kind:
			int newLineCount = 0;
			int singleCount = 0;
			int equalCount = 0;
			int lengthCount = 0;
			int upperCount = 0;
			int lowerCount = 0;
			int specialCount = 0;

			do
			{
				String[] selectedWords = new String[NUM_WORDS_PER_PASSWORD];
				for (int i = 0; i < selectedWords.length; i++)
				{
					String selectedWord;
					boolean wordValid = false;
					do
					{
						// the nextInt(bound) function returns a random value between 0 (inclusive) and the bound parameter value (exclusive)
						// (i.e. the maximum value that you can get is bound - 1)
						// In our case, we want a random value within the range of 0 - book.length for the page index
						int pageIndex = rand.nextInt(book.length);
						// Same Idea for the paragraph and line indices
						int paragraphIndex = rand.nextInt(book[pageIndex].length);
						int lineIndex = rand.nextInt(book[pageIndex][paragraphIndex].length);

						// Get the line by using all the randomly-generated indixes, split it, 
						// and then take a random word from the array of split words
						String selectedLine = book[pageIndex][paragraphIndex][lineIndex];
						String[] splitLine = selectedLine.split(" ");
						int wordIndex = rand.nextInt(splitLine.length);
						selectedWord = splitLine[wordIndex];

						// Iterate backwards to check with the previously selected words to see if we have a duplicate:
						boolean duplicateFound = false; // reset it
						for (int j = i - 1; j >= 0; j--)
						{
							if (selectedWord.equals(selectedWords[j])) // checks if two strings are exactly equal (case-sensitive)
							{
								duplicateFound = true;
								break; // use this to prematurely exit the for-loop (works with while-loops as well)
							}
						}

						boolean isSingle = (selectedWord.length() == 1);
						boolean hasNewline = selectedWord.contains("\n");

						wordValid = !isSingle && !hasNewline && !duplicateFound;

						// Increment the counts
						if (isSingle)
						{
							singleCount++;
						}
						else if (hasNewline)
						{
							newLineCount++;
						}
						else if (duplicateFound)
						{
							equalCount++;
						}

					} while (!wordValid);
					// ^ we go back and pick another word if we have a single-character word, a \n, or if the word is a duplicate

					selectedWords[i] = selectedWord;
				}

				// Concatenate all the selected words to form the password
				password = ""; // First, we have to reset the password to an empty string
				for (int i = 0; i < selectedWords.length; i++)
				{
					password += selectedWords[i]; 
				}

				// Here we will check the conditions for a correct password 
				passwordValid = true;
				if (password.length() < MIN_PASSWORD_LENGTH || password.length() > MAX_PASSWORD_LENGTH)
				{
					passwordValid = false;
					lengthCount++;
				}
				else
				{
					/* 
	                        A char is a type that represents a character. 
	                        However, chars also have an associated numerical (integer) value.
	                        The standard for which numerical value is assigned to each character
	                        is ASCII (American Standard Code for Information Interchange) (note: ASCII is pronounced "as-key").

	                        You can see the entire table here: https://www.asciitable.com/

	                        From the table, we can see the letters of the alphabet are next to each other, 
	                        as well as the numbers and special characters.

	                        So to check if a character is uppercase or lowercase or if it's of a certain other type
	                        (without using the Character class), we can check its value  and see what
	                        kind of character it corresponds to by checking which range of values it fits in.

	                        From https://www.geeksforgeeks.org/check-if-a-string-contains-uppercase-lowercase-special-characters-and-numeric-values/:

	                        If the ASCII value lies in the range of [65, 90], then it is an uppercase letter.
	                        If the ASCII value lies in the range of [97, 122], then it is a lowercase letter.
	                        If the ASCII value lies in the range of [48, 57], then it is a number.
	                        If the ASCII value lies in the ranges [32, 47], [58, 64], [91, 96] or [123, 126], then it is a special character.

	                        So we will iterate through the characters of our password string, and, 
	                        for each character, we will check which ASCII range it falls under to determine its type.
					 */

					boolean containsUpperCase = false;
					boolean containsLowerCase = false;
					int numSpecialCharacters = 0;
					for (int i = 0; i < password.length(); i++)
					{
						char ch = password.charAt(i);
						// if (ch >= 65 && ch <= 90)
						if (ch >= 'A' && ch <= 'Z') // Check for uppercase letter
						{
							containsUpperCase = true;
						}
						// else if (ch >= 97 && ch <= 122)
						else if (ch >= 'a' && ch <= 'z') // Check for lowercase letter
						{
							containsLowerCase = true;
						}
						else
						{
							numSpecialCharacters++;
						}
					}

					passwordValid = containsUpperCase && containsLowerCase && (numSpecialCharacters == NUM_SPECIAL_CHARS);

					if (!containsUpperCase)
					{
						upperCount++;
					}
					else if (!containsLowerCase)
					{
						lowerCount++;
					}
					else if (numSpecialCharacters != NUM_SPECIAL_CHARS)
					{
						specialCount++;
					}
				}

			} while (!passwordValid); // Repeat the process if any of the above conditions fail.

			System.out.println("Password = " + password + " Newline = " + newLineCount + " Single = " + singleCount 
					+ " Equal = " + equalCount + " Length = " + lengthCount + " Upper = " + upperCount 
					+ " Lower = " + lowerCount + " Special = " + specialCount);

			if (lowerCount > 0)
			{
				break; // We prematurely exit the for-loop due to encountering this condition.
			}
		}

		System.out.println("Number of passwords generated: " + numPasswords);
	}



}


