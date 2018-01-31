package algoritmo;

import java.util.Arrays;

public class OperacionBasica {
	//Suma
	public static String sum(String A, String B){
		int ke=(A.length()>B.length())?A.length():B.length();
		char[][] matrix=new char[4][ke+1];
		Arrays.fill(matrix[0],'0');
		int pa=A.length()-1;
		int pb=B.length()-1;
		for (int i = matrix[0].length-1; i >= 0; i--) {
			if(pa>=0){
				matrix[1][i]=A.charAt(pa);
				pa--;
			}else{
				matrix[1][i]='0';
			}
			if(pb>=0){
				matrix[2][i]=B.charAt(pb);
				pb--;
			}else{
				matrix[2][i]='0';
			}
		}
		for (int i = matrix[0].length-1; i >=0; i--) {
			int a=0;
			for (int j = 0; j < matrix.length-1; j++) {
				a+=Integer.parseInt(matrix[j][i]+"");
			}
			String b=a+"";
			if(b.length()==2){
				matrix[0][i-1]=b.charAt(0);
				matrix[3][i]=b.charAt(1);
			}else{
				matrix[3][i]=b.charAt(0);
			}
		}
		StringBuilder sb=new StringBuilder();
		boolean checking=false;
		for (int i = 0; i < matrix[0].length; i++) {
			if(checking||matrix[3][i]!='0'){
				sb.append(matrix[3][i]);
				checking=true;
			}
		}
		String ka=sb.toString();
		if(ka.isEmpty()){
			return "0";
		}else{
			return sb.toString();			
		}
	}
	//Resta
	public static String substract(String A, String B){
		int o1=0;
		int o2=0;
		while(o1<A.length()&&A.charAt(o1)=='0'){
			o1++;
		}
		while(o2<B.length()&&B.charAt(o2)=='0'){
			o2++;
		}
		
		if(o1==A.length()){
			o1=A.length()-1;
		}
		if(o2==B.length()){
			o2=B.length()-1;
		}
		
		int dif1=A.length()-o1;
		int dif2=B.length()-o2;
		int ke=(dif1>dif2)?dif1:dif2;
		char[][] matrix=new char[3][ke];
		
		String big=A;
		String small=B;
		if(ke!=dif1 || dif2!=ke){
			big=(dif1==ke)?A:B;
			small=(dif1==ke)?B:A;
			if(dif1!=ke){
				int temp=o1;
				o1=o2;
				o2=temp;
			}
		}else{
			int indice1=o1;
			int indice2=o2;
			while(indice1<A.length()){
				if(A.charAt(indice1)>B.charAt(indice2)){
					big=A;
					small=B;
					break;
				}else if(A.charAt(indice1)<B.charAt(indice2)){
					big=B;
					small=A;
					int temp=o1;
					o1=o2;
					o2=temp;
					break;
				}
				indice1++;
				indice2++;
			}
		}
		
		int pa=big.length()-1;
		int pb=small.length()-1;
		for (int i = matrix[0].length-1; i >= 0; i--) {
			if(pa>=o1){
				matrix[0][i]=big.charAt(pa);
				pa--;
			}else{
				matrix[0][i]='0';
			}
			if(pb>=o2){
				matrix[1][i]=small.charAt(pb);
				pb--;
			}else{
				matrix[1][i]='0';
			}
		}		
		char[] nums={'0','9','8','7','6','5','4','3','2','1'};
		for (int k = matrix[0].length-1; k >=0; k--) {
			int i=Integer.parseInt(matrix[0][k]+"");
			int j=Integer.parseInt(matrix[1][k]+"");
			int dif=j-i;
			if(dif>0){
				int indice=k-1;
				while(matrix[0][indice]=='0'){
					matrix[0][indice]='9';
					indice--;
				}
				matrix[0][indice]=((Integer.parseInt(matrix[0][indice]+"")-1)+"").charAt(0);
			}else if(dif<0){
				dif+=10;
			}
			matrix[2][k]=nums[dif];
		}
		StringBuilder sb=new StringBuilder();
		boolean checking=false;
		for (int i = 0; i < matrix[0].length; i++) {
			if(checking||matrix[2][i]!='0'){
				sb.append(matrix[2][i]);
				checking=true;
			}
		}
		String ka=sb.toString();
		if(ka.isEmpty()){
			return "0";
		}else{
			return sb.toString();			
		}
	}

}
