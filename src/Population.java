
public class Population {

	CandidateSolution[] Candidates;
	
	public Population(int popsize) {
		// TODO Auto-generated constructor stub
		Candidates = new CandidateSolution[popsize];
		// initialize population
		for(int i=0;i<popsize;i++){
			CandidateSolution candi = new CandidateSolution();
			// generate chromosome every time candi iteratees till popsize
			candi.generateCandidate();
			// save each candi that points to chromosome to index of Candidates. ie each Candidates index will have 
			// chromosome 
			saveCandidate(i,candi);
		}
	}
	// get candidate at specific index
	public CandidateSolution getCandidate(int index){
		return Candidates[index];
	}
	
	public CandidateSolution getFittest(String value){
		// create variable fittest and keep its default value as value at candodates index 0
		CandidateSolution fittest = Candidates[0];
		for(int i=0;i< getSize(); i++){
			// compare fitness till complete Candidates array and find the fittest. The fittest will work 
			// if the getFitness parramter is ONEMAX
			if(fittest.getFitness(value) <= getCandidate(i).getFitness(value));{
			//set fitness to new candidate index value which in this case would be address to object of type CandidateSolution 
			fittest = getCandidate(i);
			}
		}
		return fittest;
	}
	// will use same algorithm as in getFittest but find least value 
	public int getWorst(String value){
		CandidateSolution worst = Candidates[0];
		int worstIndex = 0;
		// loop through to get eorst candidate 
		for(int i=0;i< getSize(); i++){
			if(worst.getFitness(value) >= getCandidate(i).getFitness(value));
			worst = getCandidate(i);
			// place index of worst candidate value in worstIndex
			worstIndex = i;
		}
		// return worst index instead of value in that index
		return worstIndex;
	}
	public int getSize(){
		return Candidates.length;
	}
	public void saveCandidate(int index, CandidateSolution candi){
		Candidates[index] = candi;
	}
}
