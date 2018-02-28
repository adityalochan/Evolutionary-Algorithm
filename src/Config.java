
public class Config {
	static Config unique; 
	// define all parameters as private field here
	// …
	private int n;
	private int popsize;
	private int maxgenerations;
	@SuppressWarnings("unused")
	private String Algorithm;
	@SuppressWarnings("unused")
	private String CandidateSolution;
	private String Fitness;
	@SuppressWarnings("unused")
	private String BreedingMethod;
	private int BreedingTournamentSize;
	@SuppressWarnings("unused")
	private String MutationMethod;
	private double MutationRate;
	private int CrossoverMethod;
	private double CrossoverRate;
	private String ReplacementMethod;
	@SuppressWarnings("unused")
	private int ReplacementTournamentSize;
	
	private Config(){
	// write code here loading the parameters
	// from the property file
	// …
		// was unable to load values from property file so, set them here 
		n = 500;
		popsize= 100;
		maxgenerations= 5000;
		Algorithm= "BasicEvolutionaryAlgorithm";
		CandidateSolution= "BinaryFixedVector";
		Fitness = "OneMax";
		BreedingMethod= "Tournament";
		BreedingTournamentSize= 2;
		MutationMethod= "PerGene";
		MutationRate= 0.05;
		CrossoverMethod= 1;
		CrossoverRate= 0.8;
		ReplacementMethod= "Tournament";
		ReplacementTournamentSize= 2;
	}
	// since all values are private, accessing them using functions. 
	public int n(){
		return n;
	}
	public int popsize(){
		return popsize;
	}
	public int maxgenerations(){
		return maxgenerations;
	}
	public int BreedingTournamentSize(){
		return BreedingTournamentSize;
	}
	public double MutationRate(){
		return MutationRate;
	}
	public double CrossoverRate(){
		return CrossoverRate;
	}
	public String ReplacementMethod(){
		return ReplacementMethod;
	}
	public String Fitness(){
		return Fitness;
	}
	public int CrossoverMethod(){
		return CrossoverMethod;
	}
	
	public static Config getConfig() {
	if(unique == null) unique = new Config();
	return unique;
	}
}
