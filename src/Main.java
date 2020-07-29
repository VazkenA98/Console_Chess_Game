public class Main {

    public static void main(String[] args) {



        ChessUI chessUI = new ChessUI();
        //System.out.println("♞");
       /* StringBuffer sb=new StringBuffer();
        Scanner sc = new Scanner(System.in);
        ArrayList<String> stringList = new ArrayList<>();
        try
        {
            File file=new File("puzzles.txt");
            FileReader fr=new FileReader(file);
            BufferedReader br=new BufferedReader(fr);
            String line;
            while((line=br.readLine())!=null)
            {
                stringList.add(line);
            }
            fr.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }



        int count =1;
        for (int i=0;i<stringList.size()-2;i++){
            if(i%2==0) {
                System.out.println(count+"- "+stringList.get(i));
                count++;
            }else{
                System.out.println(stringList.get(i));
            }
        }
        System.out.println("Choose one");
        int num =  sc.nextInt();
        String chessBoard = "";
        switch (num){
            case 1:
                chessBoard = stringList.get(1);
                break;
            case 2:
                chessBoard = stringList.get(3);
                break;
            case 3:
                chessBoard = stringList.get(5);
                break;
            default:
                chessBoard = "24232225262223242121212121212121000000000000000000000000000000000000000000000000000000000000000011111111111111111413121516121314";

        }

        Chess myGame = new Chess(chessBoard);
        myGame.play();*/

   /*   Chess myGame = new Chess();
        myGame.play();*/

    }
}
