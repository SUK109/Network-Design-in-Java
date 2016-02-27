import java.util.*;


public class Project_1 {
	int N=25;
	int a[][] = new int [25][25];
	int b[][] = new int [25][25];
	int distance[][] = new int [25][25];
	int Link_Capa[][] = new int [25][25];
	int Total_Link_total_capacity[][] = new int [25][25];
	int i, j;
	int total_capacity =0;
	int total_free_link=0;
	int temp[][] = new int [25][25];
	int number=0;
	int add=0;
	int g=0;
	int total_link_used;
	float Total_Density, Total_links = (float)0.0;
	Random rand = new Random();
	public void trafficDemand() /*Calculating bij values randomly between [0, 1, 2, 3, 4]*/
	{
		int t,f;
		for(t=0;t<N;t++)
		{
			for(f=0;f<N;f++)
			{
				if(t==f)
				{
					b[t][f]=0;
				}
				else
				{
					b[t][f] = rand.nextInt(5);
				}
			}
		}
	
	}		
		
		

/* Calculating aij based on pre defined conditions (k	parameter)*/
public void unitCostfn(int K){ 
	int i, j;
	int k=K;
	int[] k_array = new int[k];
	
	for(int l=0;l<k_array.length;l++)
	{
		k_array[l] = 0;
	}
		for (i=0;i<N;i++)
		{
			
			for (j=0;j<N;j++)
			{
				if (i==j)
				{
					a[i][j] = 0;
				}
				else
				{
					a[i][j] = 200;
				}
			}
			for(int p=0;p<k;p++)
			{  
			    k_array[p]=0;

			    do{
			        int random =rand.nextInt(N);
			        boolean flag=false;
			        for(int q=0;q<p;q++)
			        {
			            if(k_array[q]==random)
			            flag=true;
			        }
			        if(!flag)
			        {
			            k_array[p]=random;
			            break;
			        }
			     }
			     while(true) ;
			}
			for (int d=0;d<k;d++)
			{
			if(i == k_array[d])
				a[i][k_array[d]+1]=1;
			
			else
				a[i][k_array[d]]=1;
				
				
			}
		
	}
		
}

/* Floyd-Warshall Algorithm */
public void floydWarshall(){

int u,v = 0;
int w;
int X, Y;
int original_cost=0;
/*Calculating original cost of the network*/
	for (X = 0; X < N; X++)
		for (Y = 0; Y < N; Y++)
			if(X !=Y)
				original_cost =original_cost+a[X][Y];
	System.out.println("The original cost of the network:" + original_cost);
	/*Calculation of shortest path */
	for (w = 0; w < N; ++w)
	{
		for (u = 0; u <N; ++u)
		{
			for (v = 0; v <N; ++v)
			{
				if ((a[u][v]>a[u][w]+ a[w][v]))
				{
					a[u][v] =a[u][w] + a[w][v];
					distance[u][v]=w;
				}
			}
		}
	}
	while(g>1)
	{

		for(u=0;u<N;u++)
		{
			for(v=0;v<N;v++)
			{
				Total_Link_total_capacity[u][v]=a[u][v]*b[u][v];
				total_capacity=total_capacity+Total_Link_total_capacity[u][v];
			}
		}
	}
	System.out.println("Floyd-Warshall Algorithm is running...");
	try
	{
	Thread.sleep(1);
	}
	catch(InterruptedException e)
	{
	System.out.println("Interrupt!!!!");
	}
	int shortestcost=0;
	/*calculation of shortest path cost */
	for (X = 0; X < N; X++)
		for (Y = 0; Y < N; Y++)
			if(X !=Y)
				shortestcost =shortestcost+distance[X][Y];
	System.out.println("The shortest path cost is:" + shortestcost);
	for(u=0;u<N;u++)
		for(v=0;v<N;v++)
		{
			if(u==v || (distance[u][v]==0))
			{
				Link_Capa[u][v]+=b[u][v];
			}
			else
			{
				distanceFunction(u,v,distance[u][v],b[u][v]);
			}
		}
		/* free links calculation*/
		for(u=0;u<N;u++)
		{
			for(v=0;v<N;v++)
			{
				if((u!=v)&&(Link_Capa[u][v]==0)&&(Link_Capa[v][u]==0)&&(temp[u][v]==0))
				{
					total_free_link = total_free_link+1;
					temp[u][v]=temp[v][u]=1;
				}
			}
		}
		
		
}

/* distance calculation*/
public void distanceFunction(int u1,int v1,int w1,int data){
		int x=0,y=0;
		x=u1;
		y=v1;number=w1;
		add = data;
		if(number==0)
		Link_Capa[x][y]+=add;
		else
		{
			Link_Capa[x][number]+=add;
			Link_Capa[i][j]+=add;
			distanceFunction(i,j,distance[i][j],add);
		}
		if (g>0)
		{
			for(u1=0;u1<N;u1++)
			{
				for(v1=0;v1<N;v1++)
				{
					Total_Link_total_capacity[u1][v1]=a[u1][v1]*b[u1][v1];
					total_capacity=total_capacity+Total_Link_total_capacity[u1][v1];
				}
			}
		}
}
/*Density and calculation*/
public void density() {
		int u2,v2=0,w2=0;
		Total_links=N*(N-1);
		for(u2=0;u2<N;u2++)
		{
			for(v2=0;v2<N;v2++)
			{
				Total_Link_total_capacity[u2][v2]=a[u2][v2]*b[u2][v2];
				total_capacity=total_capacity+Total_Link_total_capacity[u2][v2];
			}
			while(g>1)
			{
				if ((a[u2][w2] + a[w2][v2] < a[u2][v2]))
				{
					a[u2][v2] =a[u2][w2] + a[w2][v2];
					distance[u2][v2]=w2;
				}
			}
		
			
			try
			{
			Thread.sleep(1);
			}
			catch(InterruptedException e)
			{
			System.out.println("Interrupt!!!!");
			}
			total_link_used= (int)(Total_links - total_free_link);
			Total_Density=total_link_used/Total_links;
			
		}
		System.out.println("Total total_capacity of the given network:" + total_capacity);
		System.out.println("Density of the network:" + Total_Density);
		
		
	}
public static void main(String[] args) {
	
	Project_1 nf = new Project_1();
	System.out.println(" - An Application to Network Design - ");
	nf.trafficDemand();
	Scanner reader = new Scanner(System.in);
	System.out.println("Enter the value of K between 3 and 15:");
	int K = reader.nextInt();
	reader.close();
	System.out.println("The value of K you entered is " + K);
	System.out.println();
	nf.unitCostfn(K);
	nf.floydWarshall();
	nf.density();
	
	}
}
