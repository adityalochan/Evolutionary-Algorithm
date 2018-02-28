
public class BasicEA {

	public static void main(String args[]){
		// creating configuration object to use its fields in this algorithm class 
		Config cfg;
		cfg = Config.getConfig();
		//Set the length of chromosome to n from config firstly because the setChromosomeLength is static variable.
		CandidateSolution.setChromosomeLength(cfg.n());
		// Create a population reference variable of popsize
		Population mainPop = new Population(cfg.popsize());
		int currentGeneration = 0;
		// Evolve the population to maxgenerations
		for(int i=0; i< cfg.maxgenerations();i++){
        currentGeneration++;
       	// select parent1 randomly
        CandidateSolution parent1 = breedingSelection(mainPop, cfg.BreedingTournamentSize(),cfg.Fitness());
        //select parent2 randomly
        CandidateSolution parent2 = breedingSelection(mainPop, cfg.BreedingTournamentSize(), cfg.Fitness());
        
        int same = 0;
        for(int j=0;j< cfg.n();j++){
        	if(parent1.getGene(j) == parent2.getGene(j))
        	{
        	same = 1;
        	parent2 = breedingSelection(mainPop, cfg.BreedingTournamentSize(), cfg.Fitness());
        	}
        	else
        		same = 0;
        	break;
        }
        
        CandidateSolution offSpring1 = new CandidateSolution();
        CandidateSolution offSpring2 = new CandidateSolution();
        
        // using crossover rate to do crossover.If random value less than crossver then do crossover 
        if(Math.random() <= cfg.CrossoverRate()){
        	// storing value of parent1 to temp for crossover
        	CandidateSolution temp = new CandidateSolution();
        	// copying all gene values of parent1 to temp 
        	for(int j=0; j <cfg.n();j++){
        		temp.setGene(j,parent1.getGene(j));
        	}
        	offSpring1 = crossover1(parent1,parent2,cfg.CrossoverRate());
        	offSpring2 = crossover2(temp,parent2,cfg.CrossoverRate());
        }else {
        	// if random is greater than crossover rate of 0.8 then make offsrpring 
        	// equal to parents 
        	offSpring1 = parent1;
        	offSpring2 = parent2;
        }
        // mutation of offSpring1 and 2 based on MutationRate/probability
        offSpring1 = mutation(offSpring1,cfg.MutationRate());
        offSpring2 = mutation(offSpring2,cfg.MutationRate());
        

		
		//Here we do replacement. Select the worst chromosome and compare wiht offspring
        //and return it's index in mainPop if chromosome is worst than offspring
        int worstIndex1 = replaceSelection(mainPop,cfg.Fitness());
        // if mainpop candidate value is less than offspring1 fitness value then replace it mainpop
        if(mainPop.getCandidate(worstIndex1).getFitness(cfg.Fitness()) < offSpring1.getFitness(cfg.Fitness())){
        // Replace it with offSpring1 If the chromosome is more lower than offSpring's fitness
        	mainPop.saveCandidate(worstIndex1, offSpring1);//
        }
        //Select worst chromosome in mainPop again.
        int worstIndex2 = replaceSelection(mainPop,cfg.Fitness());
        if(mainPop.getCandidate(worstIndex2).getFitness(cfg.Fitness()) < offSpring2.getFitness(cfg.Fitness())){
        // Replace it with offSpring2 If the chromosome is more lower than offSpring's fitness
        	mainPop.saveCandidate(worstIndex2, offSpring2);
        }
       
        //Display current evolution state.
        System.out.println("Generation: " + currentGeneration + " Max Fitness: " + mainPop.getFittest(cfg.Fitness()).getFitness(cfg.Fitness()));
        //go to next generation process.
    }
		//Print the final result everytime through maxgeneration cycle
		System.out.println("CurrentGeneration: " + currentGeneration);
		System.out.println("generation Result is");
		System.out.println("\t Genes:");
		System.out.println(mainPop.getFittest(cfg.Fitness()));
		System.out.println("\t Fitness:");
		System.out.println(mainPop.getFittest(cfg.Fitness()).getFitness(cfg.Fitness()));
}

	// ----------------------BREEDING---------------------------------------
		
	// breeding opeeration in which random value which is fittest is selected as parent 
	public static CandidateSolution breedingSelection(Population pop, int tournamentSize, String value) {
		//Create reference variable of type Candidate solution and size tournament size
		CandidateSolution[] Candidates = new CandidateSolution[tournamentSize];  
		// getting random individual from Candidates
		int i = 0;
		while(i < tournamentSize){
			int randomId = (int) (Math.random() * pop.getSize());
			// get random values and store it in index of Candidate array
			Candidates[i] = pop.getCandidate(randomId);
			i++;
		}
		// setting fittest as Candidate value at index 0 
		CandidateSolution fittest = Candidates[0];
		CandidateSolution result = new CandidateSolution();
		// Iterates through all Candidates to find fittest of them all 
		for (int j = 0; j < tournamentSize; j++) {
       	// for value = ONEMAX get fittest. Here we compare defalut value at candidate[0] to other candidate 
       	// values
           if (fittest.getFitness(value) <= Candidates[j].getFitness(value)) {
               fittest = Candidates[j];
           	}
		}
		// set the fittest gene in result 
		for (int j = 0; j < fittest.size(); j++) {
       	result.setGene(j, fittest.getGene(j));
		}
		// return the reference variable result  
		return result;
    }
//	----------------------CROSSOVER-------------------------
	
	// doing crossover of parent
	public static CandidateSolution crossover1(CandidateSolution parent1,CandidateSolution parent2, double crossoverRate ){
		// creating o1 reference variable 
		CandidateSolution o1 = new CandidateSolution();
		// iterating through 2nd half of parent1 and setting it to parent2
		for(int i=parent1.size()/2;i<parent1.size();i++){
			parent1.setGene(i, parent2.getGene(i));
		}
		// setting modified parent equal to o1
		o1 = parent1;
		return o1;
	}
	
	// doing crossover of parent
		public static CandidateSolution crossover2(CandidateSolution temp,CandidateSolution parent2, double crossoverRate ){
			// creating o2 reference variable 
			CandidateSolution o2 = new CandidateSolution();
			// iterating through 2nd half of parent2 and setting it to temp which stores original parent1 
			for(int i=parent2.size()/2;i<parent2.size();i++){
				parent2.setGene(i, temp.getGene(i));
			}
			// setting modified parent equal to o1
			o2 = parent2;
			return o2;
		}
//		--------------------------------------MUTATION-------------------------------

		//mutate the value of offspring1
		private static CandidateSolution mutation(CandidateSolution offSpring, double mutationRate) {
	        // Iterates for all genes
	        for (int i = 0; i < offSpring.size(); i++) {
	        	/// using mutation rate to mutate 
	            if (Math.random() <= mutationRate) {        
	            	// set gene value 1 to 0 if gene value is 1
	            	if(offSpring.getGene(i) == 1){
	            		byte gene = (byte) Math.round(0);
	            		offSpring.setGene(i, gene);
	            	} else {
	            		// set gene value 1 to 0 
	            		byte gene = (byte) Math.round(1);
	            		offSpring.setGene(i, gene);
	            	}                
	            }
	        }
	        return offSpring;
	    }
		// getting the worst index 
		private static int replaceSelection(Population pop,String worstindex) {
			int fittestIndex = 0;
			fittestIndex = pop.getWorst(worstindex);    
	        return fittestIndex;
	    }	
}

