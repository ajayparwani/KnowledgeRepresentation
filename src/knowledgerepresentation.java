import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;


public class knowledgerepresentation {
    static int n;
    static int f;

    static ArrayList<String> Kb= new ArrayList<String>();
    static ArrayList<String> Constants= new ArrayList<String>();
    static ArrayList<Posneg> Positivepre= new ArrayList<Posneg>();
    static ArrayList<Posneg> Negativepre= new ArrayList<Posneg>();
    static boolean[] answers;
    static String[] queries;


    public static class Posneg{
        String pre;
        ArrayList<Integer> senno=new ArrayList<Integer>();



        Posneg(String pre, ArrayList<Integer> senno){

            this.pre=pre;
            this.senno=senno;



        }



    }
    static boolean containing(ArrayList<Posneg> Pn, String s){
        Boolean ans=false;

        for(int j=0;j<Pn.size();j++){
            //  //System.out.println(Pn.get(j).pre);
            // //System.out.println("Compared String:" + s);
            if((Pn.get(j).pre).equals(s)){
                ans=true;
            }


        }
        ////System.out.println(ans);
        return ans;
    }

    static int keyno(ArrayList<Posneg> Pn,String s){
        int ans1=0;

        for(int j=0;j<Pn.size();j++){

            if((Pn.get(j).pre).equals(s)){
                ans1=j;
            }


        }
        return ans1;



    }




    public static class Vp{
        ArrayList<String> Constants = new ArrayList<String>();
        ArrayList<String> Variables = new ArrayList<String>();

        ArrayList<String> Negativepredicates= new ArrayList<>();

        ArrayList<String> Predicates=new ArrayList<String>();
        ArrayList<Integer> Positivepredicates = new ArrayList<>();
    }

    public class fetch{

        //TODO Ask sentences in KB that have same structure as query


    }




    static void readSentences(String[] sentences,int f) {
        String temppred[] = new String[f];
        String tempconst[];
        String tempvar;


        for (int i = 0; i < f; i++) {

            String kbStanSentence="";
            temppred=sentences[i].split("[ ][|][ ]");
            // //System.out.println("Non: " + sentences[i]);

            for (int j=0;j<temppred.length;j++) {
                // //System.out.println(temppred.length);
                if(j!=0){
                    kbStanSentence += " | ";
                }
                kbStanSentence += temppred[j].split("[(]")[0] + "(";

                //temp +=
                tempvar=(temppred[j].substring(temppred[j].indexOf("(")+1));
                tempvar=tempvar.substring(0,tempvar.indexOf(")"));
                tempconst=tempvar.split("[,]");
                for(int k=0; k<tempconst.length; k++){
                    if(k!=0){
                        kbStanSentence += ",";
                    }
                    kbStanSentence += tempconst[k];
                    if(!Character.isUpperCase(tempconst[k].toCharArray()[0])){
                        kbStanSentence += i;
                    }
                }
                kbStanSentence += ")";
            }

            ////System.out.println("Sta: " + kbStanSentence);
            Kb.add(kbStanSentence);
        }


    }
    static void printkb(){

        for(int y=0;y<Kb.size();y++){

            // //System.out.println(Kb.get(y));

        }




    }

    static void con(String[] sentences,int f){

        String c[]=new String[f];
        String c1[]=new String[f];
        int k=0;
        ArrayList<Integer> negno=new ArrayList<Integer>();
        ArrayList<Integer> posno=new ArrayList<Integer>();


        for(int a=0;a<f;a++){

            c=sentences[a].split("[ ][|][ ]");

            for(int b=0;b<c.length;b++){

                c1=c[b].split("[(]");
                ////System.out.println(c1[0]);
                // //System.out.println("aj:"+c1[0].substring(c1[0].indexOf("~")+1));
                if(c1[0].toCharArray()[0]=='~') {
                    if (!containing(Negativepre,c1[0].substring(c1[0].indexOf("~") + 1))) {
                        negno = new ArrayList<>();
                        negno.add(a);
                        Negativepre.add(new Posneg(c1[0].substring(c1[0].indexOf("~") + 1),negno));
                        ////System.out.println(" Element: Key "+Negativepre.get(Negativepre.size()-1).pre + "Element Value: "+ Negativepre.get(Negativepre.size()-1).senno);

                    } else  {

                        k=keyno(Negativepre,c1[0].substring(c1[0].indexOf("~") + 1));
                        ////System.out.println("K: " + k + " Array: " + Negativepre.get(k).senno);
                        // //System.out.println("Adding another sentence no to key: "+c1[0].substring(c1[0].indexOf("~") + 1));
                        Negativepre.get(k).senno.add(a);
                        ////System.out.println(" Added "+Negativepre.get(k).pre + " Element Value: "+ Negativepre.get(k).senno);
                    }

                }




                else{
                    if (!containing(Positivepre,c1[0].substring(c1[0].indexOf("~") + 1))) {
                        posno = new ArrayList<>();
                        posno.add(a);
                        Positivepre.add(new Posneg(c1[0].substring(c1[0].indexOf("~") + 1),posno));
                        ////System.out.println(" Element: Key "+Negativepre.get(Negativepre.size()-1).pre + "Element Value: "+ Negativepre.get(Negativepre.size()-1).senno);

                    } else  {

                        k=keyno(Positivepre,c1[0].substring(c1[0].indexOf("~") + 1));
                        // //System.out.println("K: " + k + " Array: " + Positivepre.get(k).senno);
                        //  //System.out.println("Adding another sentence no to key: "+c1[0].substring(c1[0].indexOf("~") + 1));
                        Positivepre.get(k).senno.add(a);
                        // //System.out.println(" Added "+Negativepre.get(k).pre + " Element Value: "+ Negativepre.get(k).senno);
                    }



                }


            }


        }

        //String s[]=   varcollector("~Happy(x,y,z,d,c,John) | Happy(x,y,z,t,r) | ~F(x) | G(y) | F(Joe) | G(Joe)") ;

        // String str2=Unify("~F(x) | G(x)","F(Joe)");
        // String str2=Unify("Offspring(Jojo,Jojo,Jojo,Jojo,Jojo,Jojo,Jojo,Jojo)", "~Offspring(a,b,c,d,e,f,g,h)");
        //  //System.out.println("Unificated string:" +str2);
           /* for(int y=0;y<s.length;y++){
            //System.out.println(s[y]);
            }*/
        //Sprint(Positivepre,Negativepre);
    }

    static void print(ArrayList<Posneg> Postivepre,ArrayList<Posneg> Negativepre){

        for(int d=0;d<Postivepre.size();d++)
        {
            //System.out.println("Positive Key:"+ Postivepre.get(d).pre +"Value:" + Postivepre.get(d).senno);
        }
        for(int d=0;d<Negativepre.size();d++)
        {
            //System.out.println("Negative Key:"+ Negativepre.get(d).pre +"Value:" + Negativepre.get(d).senno);
        }


    }

    static String getonlyname(String s){
        char c;
        String str="";
        for(int i=0;i<s.length();i++){

            c=s.charAt(i);

            if(c=='~'){
                continue;

            }else{

                str=s.substring(i,s.indexOf('('));

                break;

            }


        }

        return str;

    }

    static String[] splitter(String s){


        return s.split("[ ][|][ ]");

    }


    static String[] varcollector(String s){

        String s1[];
        String st="";
        String s2[];


        s2=splitter(s);


        for(int w=0;w<s2.length;w++) {
            st += s2[w].substring(s2[w].indexOf("(") + 1, s2[w].indexOf(")"));
            st+=",";
        }
        s1=st.split("[,]");

            /*for(int i=0;i<s1.length;i++){
                //System.out.println("varcollector: "+s1[i]);
            }*/
        return s1;

    }

    static boolean isconst(String s){
        return !isvar(s);

    }
    static boolean isvar(String s){

        return Character.isLowerCase(s.toCharArray()[0]);

    }

    static boolean isneg(String s){

        return s.toCharArray()[0]=='~';


    }




    static String Unify(String sen, String que){

        String d[];
        String S1;
        String d1[];
        HashMap<String,String> Evaluater= new HashMap<>();
        d=splitter(que);
        S1=d[0];
        Boolean flag=false;
        d1=splitter(sen);
        int te=-1;
        String unifiedsen="";
        String queuni="";
        String storesen[];
        String storeque[];
        String sendback="";
        ArrayList<String> unistr=new ArrayList<>();

        // //System.out.println("Sentence length at start is: "+ d1.length);
        for(int i=0;i<d1.length;i++){

            if(getonlyname(d1[i]).equals(getonlyname(S1))  && ((isneg(d1[i]) && !(isneg(S1))) ||(!isneg(d1[i])&& (isneg(S1)))  )  ){

                Evaluater=realunifier(d1[i],S1);

                if(Evaluater==null){
                    continue;
                }else{
                    flag=true;
                    te=i;
                    break;
                }




            }



        }
        if(flag==false){

            return null;
        }else{
            // //System.out.println("Hashmap contents: "+ Evaluater.get("y"));
            // //System.out.println("Length of sentence after hashmap recieved: "+ d1.length + " Value of te: "+te);
            for(int h=0;h<d1.length;h++){
                unifiedsen="";
                if(h!=te){

                    if(isneg(d1[h])){
                        unifiedsen+="~";
                    }

                    unifiedsen+=getonlyname(d1[h]);
                    unifiedsen+="(";

                    storesen=varcollector(d1[h]);

                    for(int g=0;g<storesen.length;g++){

                        if(g!=0){
                            unifiedsen+=",";
                        }
                        if(Evaluater.containsKey(storesen[g])){

                            unifiedsen+=Evaluater.get(storesen[g]);

                        }else{

                            unifiedsen+=storesen[g];
                        }


                    }

                    unifiedsen+=")";

                    unistr.add(unifiedsen);


                }


                //todo if storesen doesn't work like this then clear its value here

            }
                /*if(!(unifiedsen.equals(""))) {
                    unifiedsen += " | ";
                }*/
            //  //System.out.println("Query length is: " + d.length);
            for(int y=0;y<d.length;y++){
                queuni="";

                if(y!=0){
                    // //System.out.println("Reached here with y value as: "+ y);
                    if(isneg(d[y])){
                        queuni+="~";
                    }

                    queuni+=getonlyname(d[y]);
                    queuni+="(";

                    storeque=varcollector(d[y]);

                    for(int z=0;z<storeque.length;z++){

                        if(z!=0){
                            queuni+=",";
                        }
                        if(Evaluater.containsKey(storeque[z])){

                            queuni+=Evaluater.get(storeque[z]);

                        }else{

                            queuni+=storeque[z];
                        }


                    }


                    queuni+=")";
                    unistr.add(queuni);

                }





            }







        }

        for(int e=0;e<unistr.size();e++){

            sendback+=unistr.get(e);
            if(!(e==unistr.size()-1)) {
                sendback += " | ";
            }
        }

        //System.out.println(":" + sendback + ":");

        return sendback;





    }


    static HashMap<String,String> realunifier(String sen1,String que1){

        HashMap<String,String> Supersentencer=new HashMap<>();

        String str[];
        String qtr[];

        // //System.out.println("Sentence is: " +sen1);
        str=varcollector(sen1);
        qtr=varcollector(que1);
        for(int x=0;x<str.length;x++){
            // //System.out.println("str stored: " + str[x]);
        }
        // //System.out.println("sen length: "+ str.length +"first: " + str[0]);
        // //System.out.println("que length: "+ qtr.length+ "first: "+ qtr[0]);


        for(int i=0;i<str.length;i++){
            //  //System.out.println("Reached here: with sentence length : "+ str.length);
            if(isconst(str[i]) && isconst(qtr[i])) {

                if (str[i].equals(qtr[i])) {

                    continue;


                } else {
                    ////System.out.println("Here");
                    return null;
                }

            }
            if(isvar(str[i]) && isconst(qtr[i])){


                if ( Supersentencer.containsKey(str[i])){

                    if(Supersentencer.get(str[i]).equals(qtr[i])){

                        continue;

                    }else{
                        return null;
                    }

                }else{
                    //System.out.println("{" + str[i] + "/" + qtr[i] + "}");
                    Supersentencer.put(str[i],qtr[i]);

                }

            }


            if(isconst(str[i]) && isvar(qtr[i])){

                if(Supersentencer.containsKey(qtr[i])){

                    if(Supersentencer.get(qtr[i]).equals(str[i])){

                        continue;

                    }else{

                        return null;

                    }

                }else{
                    //System.out.println("{" + qtr[i] + "/" + str[i] + "}");
                    Supersentencer.put(qtr[i],str[i]);



                }





            }

            if(isvar(str[i]) && isvar(qtr[i] )){

                if(Supersentencer.containsKey(str[i]) && Supersentencer.containsKey(qtr[i])){

                    if(Supersentencer.get(str[i]).equals(Supersentencer.get(qtr[i]))){
                        continue;
                    }else{
                        return null;
                    }


                }
                if(Supersentencer.containsKey(str[i])){
                    //System.out.println("{" + qtr[i] + "/" + Supersentencer.get(str[i]) + "}");
                    Supersentencer.put(qtr[i],Supersentencer.get(str[i]));

                }

                if(Supersentencer.containsKey(qtr[i])){
                    //System.out.println("{" + str[i] + "/" + Supersentencer.get(qtr[i]) + "}");
                    Supersentencer.put(str[i],Supersentencer.get(qtr[i]));


                }
                if(!(Supersentencer.containsKey(str[i]) || Supersentencer.containsKey(qtr[i]))){

                    Supersentencer.put(str[i],qtr[i]);
                }
                //TODO where both are not present in hashmap
               // //System.out.println("Hohoho");


            }











        }



        return Supersentencer;



    }

    static String negated(String s){
        String s1="";
        if(s.toCharArray()[0]==('~')){
            s1+=s.substring(1,s.indexOf(")"));
            s1+=")";
            return s1;
        }else{
            s1+="~";
            s1+=s;
            return s1;
        }


    }




    static void initialfunc(){
        answers = new boolean[queries.length];
        for(int x=0;x<queries.length;x++){
            Kb.add(negated(queries[x]));
            answers[x] = resolver(negated(queries[x]),0);
            Kb.remove(Kb.size()-1);
            //printanswer(answers[x]);
        }




    }

    static void printanswer(){
        try{
            PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
            //writer.println("OK");


            for(int i=0; i<answers.length; i++){
                if(answers[i]){
                    writer.println("TRUE");
                    //System.out.println("TRUE");
                }else{
                    writer.println("FALSE");
                    //System.out.println("FALSE");
                }
            }
            writer.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }


    }


    static boolean resolver(String query, int depth){

        if(depth>500){
            return false;
        }


        ArrayList<Integer> A1=new ArrayList<>();
        String some[];
        Boolean test=false;
        Boolean test1=false;
        String str="";

        if(Kb.contains(negated(query))){
            return true;
        }


        some=splitter(query);

        if(validitycheck(some)){
            return true;
        }

        //System.out.println("Resolving: " + some[0]);

        if(isneg(some[0])){

            for(int x=0;x<Positivepre.size();x++){

                if(getonlyname(some[0]).equals(Positivepre.get(x).pre)){

                    A1=Positivepre.get(x).senno;
                    test=true;
                }

            }


        }else{

            for(int x=0;x<Negativepre.size();x++){

                if(getonlyname(some[0]).equals(Negativepre.get(x).pre)){

                    A1=Negativepre.get(x).senno;
                    test=true;
                }

            }


        }

        if(!test){
            return false;
        }

        boolean success = false;

        for(int y=0;y<A1.size();y++){

            //System.out.println("Unifying: "+ Kb.get(A1.get(y)) + "::" + query);
            str=Unify(Kb.get(A1.get(y)),query);
            //System.out.println("Unified Sentence:"+ str);
            if(str==null){

            } else if(str.equals("")){
                success = true;
                break;
            } else{
                if(resolver(str, depth+1)){
                    success = true;
                    break;
                }
            }
        }






        return success;


    }

    static Boolean validitycheck(String Str[]){

        for(int i=0;i<Str.length;i++){

            for(int j=i+1;j<Str.length;j++){

                if(getonlyname(Str[i]).equals(getonlyname(Str[j]))){
                        //System.out.println("In validity: ");
                    if ((!isneg(Str[i]) && isneg(Str[j]))) {
                        if(Str[j].equals( "~" + Str[i])){
                            return true;
                        }
                    } else if((isneg(Str[i]) && !isneg(Str[j]))){
                        if(Str[i].equals( "~" + Str[j])){
                            return true;
                        }
                    }
                }
            }
        }

        return false;

    }



    public static void main(String args[]){



        int counter = 0;
        int count=0;


        String fileName = "input.txt";

        // This will reference one line at a time
        String line = null;
        String line1=null;


        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String strN = bufferedReader.readLine();


            n = Integer.parseInt(strN);


            queries = new String[n];

            // //System.out.println(n);
            while ( counter < n) {
                line = bufferedReader.readLine();
                queries[counter] = line;
                //  //System.out.println(queries[counter]);
                counter++;

            }


            String strf = bufferedReader.readLine();
            f = Integer.parseInt(strf);
            String kb[]=new String[f];

            while (count < f) {
                line1 = bufferedReader.readLine();
                kb[count] = line1;
                // //System.out.println(kb[count]);
                count++;

            }

            readSentences(kb,f);
            con(kb,f);
            //printkb();



            bufferedReader.close();

        }  catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //String str2=Unify("~F(x) | G(x)","F(y) | P(y)");
        //String str2=Unify("Offspring(Jojo,Jojo,Jojo,Jojo,Jojo,Jojo,Jojo,Jojo)", "~Offspring(a,b,c,d,e,f,g,h)");
       // //System.out.println("Result: " + str2);
        if(queries!=null){
            initialfunc();

            printanswer();
        }




    }


}
