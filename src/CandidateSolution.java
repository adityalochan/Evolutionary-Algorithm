public class CandidateSolution {
	static int geneLength = 10;
	// chromosome are sum byte array of ie collection of gene 
	byte[] chromosome = new byte[geneLength];
	double fitness = 0;
	public void generateCandidate(){
		for(int i=0;i<size();i++){
			// will create random values and round them up to give binary bits 
			byte gene = (byte) Math.round(Math.random());
			chromosome[i] = gene;
		}
	}
	// set length of chromsome 
	public static void setChromosomeLength(int value){
		geneLength = value;
	}
	// get gene ie particular value at index of chromosome
	public int getGene(int index){
		return chromosome[index];
	}
	// 
	public void setGene(int index, int value){
		// setting value of gene at particular index of chromosome
		chromosome[index] = (byte) value;
		fitness = 0;
	}
	public int size(){
		// get length of chromosome 
		return chromosome.length;
	}
	public String toString(){
		// adding "" notation with any int value gives us string value 
		String genesString = "";
        for (int i = 0; i < geneLength; i++) {
        	// giving string of chromosome
            genesString += chromosome[i];
        }
        return genesString;
	}
	//getting fitness of chromosome, to calculate fitness, take sum of 
	// complete fitness and divide it by total number of fitness to get probability 
	public double getFitness(String value){
		int fitness_temporary = 0;
		// get fitness only if the fitness value is equal to string ONEMAX
		if(value.equals("OneMax")){
		for(int i=0;i<geneLength;i++){
			// total gene sum 
			fitness_temporary += (int) chromosome[i];
		}
		// probaility as fitness 
		fitness = (double)fitness_temporary/(double)geneLength;
		}
		return fitness;
	}
}
