import java.io.File;

public class WordCount{
    //主函数
    public static void main(String[] args){
        if(args.length==0)
        {
            System.out.println("没有参数");
        }
        else if(args.length==1)
        {
            System.out.println("输入参数只有一个");
        }
        else if(args.length==2)
        {
            File in=new File(args[0]);
            File out=new File(args[1]);

            if(!in.exists())
            {
                System.out.println("输入文件不存在");
            }
            else if(!out.exists())
            {
                System.out.println("输出文件不存在");
            }
            else
            {
                new HandleTxt(in,out);
            }
        }
    }
}