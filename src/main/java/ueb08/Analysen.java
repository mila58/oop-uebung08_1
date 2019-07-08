package ueb08;

import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.util.*;
import java.util.LinkedList;


class Analysen {

	/**
	 * Wie viele Tore fallen durchschnittlich in jedem Spiel?
	 */
	static double torstatistikenToreProSpiel() throws IOException {
		Bundesliga bl = Bundesliga.loadFromResource();
		double tore = 0;
		for(Spiel s: bl.spiele){
			tore += s.getToreGast() + s.getToreHeim();
		}
		return tore/bl.spiele.size();

	}

	/**
	 * Wie viele Tore fallen durchschnittlich in einem Spiel der 1. Liga?
	 */
	static double torstatustikenToreProErstligaspiel() throws IOException {
		Bundesliga bl = Bundesliga.loadFromResource();
		List <Integer> ersteL = new LinkedList<>();
		for(Verein v: bl.vereine.values()){
			if(v.getLiga() == 1){
				ersteL.add(v.getId());
			}
		}
		double spiele = 0;
		int tore = 0;
		for(Spiel s: bl.spiele){
			if(ersteL.contains(s.getHeim())){
				tore += s.getToreGast()+s.getToreHeim();
				spiele ++;
			}
		}
		return tore/spiele;
	}

	/**
	 * Wie viele Tore fallen durchschnittlich an einem Spieltag der 2. Liga?
	 */
	static double torstatistikenToreProSpieltag2teLiga() throws IOException {
		Bundesliga bl = Bundesliga.loadFromResource();
		List <Integer> zweiteL = new LinkedList<>();
		for(Verein v: bl.vereine.values()){
			if(v.getLiga() == 2){
				zweiteL.add(v.getId());
			}
		}

		double tore = 0;
		double spielT = 0;
		for(Spiel s: bl.spiele){
			if(zweiteL.contains(s.getHeim())){
				tore += s.getToreGast()+ s.getToreHeim();
				if (spielT < s.getSpieltag()){
					spielT = s.getSpieltag();
				}
			}
		}

		return tore/spielT;
	}

	/**
	 * Stimmt es, dass in den Nachmittagsspielen (15:30:00) im Schnitt mehr Tore fallen, wie in den Abendspielen?
	 */
	static boolean torstatistikenToreNachmittagsAbends() throws IOException {
		Bundesliga bl = Bundesliga.loadFromResource();
		double nachmT = 0;
		int nachm =0;
		double abendT = 0;
		int abend = 0;
		for(Spiel s: bl.spiele){
			if(s.getUhrzeit().equals("15:30:00")){
				nachmT += s.getToreGast()+s.getToreHeim();
				nachm ++;
			}else
				abendT += s.getToreGast()+ s.getToreHeim();
			abend ++;
		}
		if(nachmT/nachm > abendT/abend){
			return true;
		}else
			return false;
	}

	/**
	 * Stimmt es, dass Vereine der 3. Liga zuhause im Schnitt mehr Tore schießen als auswärts?
	 */
	static boolean torstatistikenToreDaheim() throws IOException {
		Bundesliga bl = Bundesliga.loadFromResource();
		List <Integer> dritteL = new LinkedList<>();
		for(Verein v: bl.vereine.values()){
			if(v.getLiga() == 3){
				dritteL.add(v.getId());
			}
		}

		double toreHeim = 0;
		double toreAusw = 0;
		double spiele = 0;
		for(Spiel s: bl.spiele){
			if(dritteL.contains(s.getId())){
				toreHeim += s.getToreHeim();
				toreAusw += s.getToreGast();
				spiele ++;
			}
		}
		if(toreHeim/spiele > toreAusw/spiele){
			return true;
		}else
			return false;
	}

	/**
	 * Wie viele Tore hat der FC Bayern München (Verein 1) erzielt?
	 */
	static int vereineToreVerein1erzielt() throws IOException {
		Bundesliga bl = Bundesliga.loadFromResource();
		int tore = 0;
		for(Spiel s: bl.spiele){
			if(s.getHeim() == 1){
				tore += s.getToreHeim();
			}else if (s.getGast() == 1)
				tore += s.getToreGast();
		}
		return tore;
	}

	/**
	 * Wie viele Tore hat der FC Schalke 04 (Verein 2) erhalten?
	 */
	static int vereineToreVerein2erhalten() throws IOException {
		Bundesliga bl = Bundesliga.loadFromResource();
		int tore = 0;
		for(Spiel s: bl.spiele){
			if(s.getHeim() == 2){
				tore += s.getToreGast();
			}else if (s.getGast() == 2)
				tore += s.getToreHeim();
		}
		return tore;
	}

	/**
	 * Wie viele Punkte hat der 1. FC Nürnberg (Verein 20)?
	 * Ein Sieg zählt 3 Punkte, ein Unentschieden 1, eine Niederlage 0 Punkte.
	 */
	static int vereineToreVerein20punkte() throws IOException {
		Bundesliga bl = Bundesliga.loadFromResource();
		int punkte = 0;
		for(Spiel s: bl.spiele){
			if(s.getHeim() == 20){
				if(s.getToreHeim() > s.getToreGast()){
					punkte+= 3;
				} else if(s.getToreHeim() == s.getToreGast()){
					punkte += 1;
				}else
					continue;

			}
			if(s.getGast() == 20){
				if(s.getToreGast() > s.getToreHeim()){
					punkte+= 3;
				} else if(s.getToreGast() == s.getToreHeim()){
					punkte += 1;
				}else
					continue;

			}
		}
		return punkte;
	}

	/**
	 * Was ist das Torverhältnis des VfL Bochum (Verein 26)?
	 */
	static int vereineTorverhaeltnis26() throws IOException {
		Bundesliga bl = Bundesliga.loadFromResource();
		int toreges = 0;
		int torekas = 0;
		for(Spiel s: bl.spiele){
			if(s.getHeim() == 26){
				toreges += s.getToreHeim();
				torekas += s.getToreGast();
			}else if(s.getGast() == 26){
				toreges += s.getToreGast();
				torekas += s.getToreHeim();
			}
		}

		return toreges-torekas;
	}

	/**
	 * Hilfsklasse.
	 */
	static class VereinTore {
		String verein;
		int tore;

		public VereinTore(String v, int t) {
			this.verein = v;
			this.tore = t;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (!(o instanceof VereinTore))
				return false;
			VereinTore that = (VereinTore) o;
			return tore == that.tore && verein.equals(that.verein);
		}
	}


	/**
	 * Welche drei Vereine haben die meisten Tore zuhause geschossen, und wie viele?
	 */
	static List<VereinTore> vereineMeisteToreZuhause() throws IOException {
		Bundesliga bl = Bundesliga.loadFromResource();
		Map <Integer, Integer> toreZuhause = new HashMap<>();

		for(Spiel s: bl.spiele){
			int tore = 0;
			if(toreZuhause.containsKey(s.getHeim())){
				tore = toreZuhause.get(s.getHeim());

			toreZuhause.put(s.getHeim(),tore += s.getToreHeim());

		}

		List <VereinTore> liste = new LinkedList<>();

		for(Map.Entry<Integer, Integer> e : toreZuhause.entrySet()){
			Verein v = bl.vereine.get(e.getKey());
			Integer toreZh = e.getValue();
			liste.add(new VereinTore(v.getName(),toreZh));
		}

		liste.sort(new Comparator <VereinTore>() {
			@Override
			public int compare(VereinTore o1, VereinTore o2){
				return Integer.compare(o2.tore, o1.tore);
			}
		});
		return liste.subList(0,3);
	}

	/**
	 * Welcher Verein hat die wenigsten Tore auswärts geschossen, und wie viele?
	 */

	static VereinTore vereineWenigsteToreAuswaerts()throws IOException {
		Bundesliga bl = Bundesliga.loadFromResource();
		Map <Integer, Integer> toreAuswaerts = new HashMap<>();

		for(Spiel s: bl.spiele){
			int tore = 0;
			if(toreAuswaerts.containsKey(s.getGast()));
				tore = toreAuswaerts.get(s.getGast());

			toreAuswaerts.put(s.getGast(), tore += s.getToreGast());
			}
		}

		VereinTore schlechtester = new VereinTore(" ", Integer.MAX_VALUE);

	for(Map.Entry <Integer, Integer> e: toreAuswaerts.entrySet()){
		Verein v = bl.vereine.get(e.getKey());
		int toreH = e.getValue();
		if(toreH < schlechtester.tore){
			schlechtester = new VereinTore(v.getName(),toreH);
		}
		}
		return schlechtester;
	}
}