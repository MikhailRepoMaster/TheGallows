import java.util.Random;
import java.util.Scanner;

class Progrsss 
{
    public static void main(String[] args) 
    {
        Gallows gallows = new Gallows();
        gallows.Reshatel();
        
    }
}

class Gallows 
{
    private int random_word;
    private int start_end_game;
    private char letter;
    private String input;
    private int attempts;
    Scanner scanner = new Scanner(System.in);
    private boolean[] guessedLetters;

    private String[] questWord = {"Vanquished", "Country", "Sea", "Phone", "Computer", "Building", "Keyboard", "Bed", "Fence", "Nothing"};

    public void Reshatel() 
    {
        System.out.println("Вы хотите начать новую игру или выйти? 1/0\n");
        start_end_game = scanner.nextInt();

        if (start_end_game == 0) 
        {
            scanner.close();
            System.out.println("Всего хорошего!");
        } 
        else if (start_end_game == 1) 
        {
            System.out.println("Правила игры:\nСуть игры заключается в том, что игрок угадывает слово, предлагая буквы, и при каждом неправильном угадывании выпадает часть фигуры виселицы. Игра продолжается до тех пор, пока слово не будет угадано или фигура виселицы не будет собрана полностью.\n");
            pressEnterToContinue();

            System.out.print("\033[H\033[2J");
            System.out.flush();

            String secretWord = QuestWord().toLowerCase();
            guessedLetters = new boolean[secretWord.length()];

            System.out.println("Слово загадано. Удачи!");

            attempts = 0;
            while (attempts < 7) 
            {
                displayCurrentProgress(secretWord);
                System.out.print("Введите букву: ");
                input = scanner.next().toLowerCase();

                if (input.length() > 1) 
                {
                    System.out.println("Пожалуйста, введите одну букву.");
                    continue;
                }

                letter = input.charAt(0);
                boolean correctGuess = false;

                for (int i = 0; i < secretWord.length(); i++) 
                {
                    if (secretWord.charAt(i) == letter) 
                    {
                        guessedLetters[i] = true;
                        correctGuess = true;
                    }
                }

                if (!correctGuess) 
                {
                    attempts++;
                    System.out.println("Неверно! Осталось попыток: " + (7 - attempts));
                }

                VisualDeath(attempts);
                    
                

                if (isWordGuessed()) 
                {
                    System.out.println("Поздравляем! Вы угадали слово: " + secretWord);
                    return;
                }
            }

            System.out.println("Вы проиграли! Загаданное слово было: " + secretWord);
        } 
        else 
        {
            System.out.println("Неверный выбор!");
            Reshatel();
        }
    }

    private void pressEnterToContinue() 
    {
        System.out.println("Нажмите любую клавишу: ");
        try 
        {
            System.in.read();
            scanner.nextLine();
        } 
        catch (Exception e) 
        {
            System.out.println("Что-то не так(");
        }
    }

    private String QuestWord() 
    {
        Random rnd = new Random();
        random_word = rnd.nextInt(10);
        return questWord[random_word];
    }

    private void displayCurrentProgress(String secretWord) 
    {
        for (int i = 0; i < secretWord.length(); i++) 
        {
            if (guessedLetters[i]) 
            {
                System.out.print(secretWord.charAt(i) + " ");
            } 
            else 
            {
                System.out.print("_ ");
            }
        }
        System.out.println();
    }

    private void VisualDeath (int attempts)
    {
        switch (attempts) {
            case 1:
                System.out.println("|");
                break;
            case 2:
                System.out.println("|\no");
                break;
            case 3:
                System.out.println("|\no\n|");
                break;
            case 4:
                System.out.println(" |\n o\n/|");
                break;
            case 5: 
                System.out.println(" |\n o\n/|\\");
                break;
            case 6:
                System.out.println(" |\n o\n/|\\\n/");
                break;
            case 7:
                System.out.println(" |\n o\n/|\\\n/ \\");
                break;
            default:
                break;
        }
    }

    private boolean isWordGuessed() 
    {
        for (boolean guessed : guessedLetters) 
        {
            if (!guessed) 
            {
                return false;
            }
        }
        return true;
    }
}
