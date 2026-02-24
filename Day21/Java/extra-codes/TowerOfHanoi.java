package sunbeam;

public class TowerOfHanoi {
	public static void toh(int disk, char from, char to, char aux) {
		if(disk == 0)
			return;
		toh(disk-1, from, aux, to);
		System.out.println("Move " + disk + " from " + from + " to " + to);
		toh(disk-1, aux, to, from);
	}
	public static void main(String[] args) {
		toh(4, 'A', 'B', 'C');
	}
}
