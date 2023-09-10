import java.util.*;
import java.text.*;
public class MatrixSolver
{
   public static void main(String[]args)
   {
      double[][] d=inputs();
      ref(d);
      matrixToString(d); 
   }
   public static double[][] inputs()
   {
      Scanner s=new Scanner(System.in);
      System.out.print("Rows?");
      int r=s.nextInt();
      System.out.print("Cols?");
      int c=s.nextInt();
      double[][] d=new double[r][c];
      int[][] view=new int[r][c];
      for(int i=0;i<r*c;i++)
         view[i/c][i%c]=i+1;
      matrixToString(view);
      System.out.println("\n\nFill the array with 1. Random Vars or 2.Chosen Vars");
      if(s.nextInt()!=1)
      {
         for(int i=0;i<r*c;i++)
         {
            System.out.print("Num "+(i+1)+"? ");
            d[i/c][i%c]=s.nextInt();
         }
      }
      else
      {
         for(int i=0;i<r*c;i++)
            d[i/c][i%c]=((int)(Math.random()*2));
      }
      return d;          
   }
   public static void matrixToString(double[][] d)
   {
      DecimalFormat df=new DecimalFormat("+0.00;-0.00");
      System.out.println();
      for(int i=0;i<d.length;i++)
      {
         System.out.print("\n[");
         for(int j=0;j<d[0].length-1;j++)
            System.out.print(df.format(d[i][j])+",  ");
         System.out.print(df.format(d[i][d[0].length-1]));
         System.out.print("]");
      }
   }
   public static void matrixToString(int[][] d)
   {
      System.out.println();
      for(int i=0;i<d.length;i++)
      {
         System.out.print("\n[");
         for(int j=0;j<d[0].length-1;j++)
            System.out.print(d[i][j]+",  ");
         System.out.print(d[i][d[0].length-1]);
         System.out.print("]");
      }
   }
   public static void ref(double[][] matrix)
   {
      matrixToString(matrix);
      int m= matrix.length, n= matrix[0].length, i=0;
      double[] temp=new double[matrix.length];
      int[] p = {0, 0}; // Stores the location of the p [i, j]
      double leadingEntry;
      while(true) 
      {
         p[0]=i;
         while(matrix[p[0]][p[1]]==0.0)
         {
            p[0]++;
            if(p[0]>= m)
               break;
            if(matrix[p[0]][p[1]]!=0.0)
            {
               temp=matrix[p[0]];
               matrix[p[0]]=matrix[i];
               matrix[i]=temp;
               p[0]=i;
            }
         }
         for (int r = p[0]; r < m; r++) 
         {
            leadingEntry = matrix[r][p[1]];
            for (int c = 0; c < n && leadingEntry != 0; c++) 
            {
               matrix[r][c] /= leadingEntry;
            }
         }
         for (int r= p[0] + 1; r < m; r++) 
         {
            if(matrix[r][p[1]]!=0)
            {
               for (int c = 0; c < n; c++) 
                  matrix[r][c] -= matrix[p[0]][c];
            }
         }
         if(++p[1]>=n||++i>=m)
            break;
      }
      matrixToString(matrix);
      System.out.print("\n\nRow Reducing");
      p[0]=matrix.length-1;
      while(true)
      {
         p[1]=0;
         while(matrix[p[0]][p[1]]==0)
         {
            if(++p[1]>=n)
            {
               p[1]=0;
               if(--p[0]<0)
                  return;
            }
         }
         if(matrix[p[0]][p[1]]!=1)
         {
            for(int j=p[1];j<matrix[0].length;j++)
               matrix[p[0]][j]/=matrix[p[0]][p[1]];
         }
         for(i=p[0]-1;i>=0;i--)
         {
            if(matrix[i][p[1]]!=0)
            {
               for(int j=matrix[0].length-1;j>=p[1];j--)
               {
                  matrix[i][j]/=matrix[i][p[1]];
                  matrix[i][j]-=matrix[p[0]][j];
               }
                
            }
         }
         if(--p[0]<0)
            return;
      }     
   }
}