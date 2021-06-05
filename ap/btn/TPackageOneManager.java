 
package ap.btn; 

import java.io.File; 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.Comparator; 
import java.util.Date; 
import java.util.LinkedHashMap; 
import java.util.List; 
import java.util.Locale; 
import java.util.Map; 
import java.util.Map.Entry; 
import java.util.stream.Collectors;

import ap.explorer.Range;
import ap.global.gl; 
import ap.state.Fl; 
import ap.utils.CandleUtil; 
import ap.utils.DateUtil; 
import ap.utils.Fu; 
import ap.utils.MaUtil; 
import ap.utils.MapUtils; 
import ap.utils.Su; 

public class TPackageOneManager { 

	private String ticker; 
	 
	private List<TPackageOne> items ; 
	 
	 
	public String getTicker() { 
		return ticker; 
	} 

	public void setTicker(String ticker) { 
		this.ticker = ticker; 
	} 

	public List<TPackageOne> getItems() { 
		return items; 
	} 

	public void setItems(List<TPackageOne> items) { 
		this.items = items; 
	} 

	public TPackageOneManager() { 
		 
		items = new ArrayList<TPackageOne>(); 
		 
	} 
	public TPackageOneManager(String ticker) { 
		 
		this(); 
		 
		this.setTicker(ticker); 
	} 
	 
	public static TPackageOneManager getInstance() 
	{ 
		return new TPackageOneManager(); 
	} 
	 
	public static TPackageOneManager getInstance(String ticker) 
	{ 
		return new TPackageOneManager(ticker); 
	} 
	 
	public boolean loadRest() 
	{ 
		// Reload OHLC in local variable. 
		 
		List<Bar> list = new ArrayList<Bar>(); 
		 
		this.getItems().forEach( 
				 
				a-> 
				{ 
					list.add(a.getTbi()); 
				} 
				); 
		 

		 
		if(list.size()== gl.E_EMPTY) 
		{ 
			gl.tracex(new Object(){},String.format("Reload OHLC...%s...rows...%06d","Error",list.size())); 
			 
			return false; 
		} 
		 
			gl.tracex(new Object(){},String.format("Reload OHLC...%s...rows...%06d","Ok",list.size())); 
		 
			this.items.clear(); 
		 
		List<Bar> m3 = new ArrayList<Bar>(); 
		 
		List<Bar> m5 = new ArrayList<Bar>(); 
		 
		List<Bar> m7 = new ArrayList<Bar>(); 
		 
		List<Bar> m10 = new ArrayList<Bar>(); 
		 
		List<Bar> m14 = new ArrayList<Bar>(); 
		 
		List<Bar> m21 = new ArrayList<Bar>(); 
		 
		List<Bar> m33 = new ArrayList<Bar>(); 
		 
		List<Bar> m55 = new ArrayList<Bar>(); 
		 
		List<Bar> m100 = new ArrayList<Bar>(); 
		 
		List<Bar> m144 = new ArrayList<Bar>(); 
		 
		List<Bar> m200 = new ArrayList<Bar>(); 
		 
		int[] cnt = {0,0,0,0,0,0}; 
		 
		Bar[] 	p_tbi = { Bar.get_instance() }; 

		TSbhl[] 	p_sbhl = { TSbhl.getInstance() }; 

		TMa[] 		p_ma = { TMa.getInstance() }; 

		TDays[] 	p_dows = { TDays.getInstance() }; 

		TCross[] 	p_cross = { TCross.getInstance() }; 

		list.forEach(a->{ 
			 
			m3.add(a); 
			 
			m5.add(a); 
			 
			m7.add(a); 
			 
			m10.add(a); 
			 
			m14.add(a); 
			 
			m21.add(a); 
			 
			m33.add(a); 
			 
			m55.add(a); 
			 
			m100.add(a); 
			 
			m144.add(a); 
			 
			m200.add(a); 
			 
			double avg3 = 0.0d; 
			 
			double avg5 = 0.0d; 
			 
			double avg7 = 0.0d; 
			 
			double avg10 = 0.0d; 
			 
			double avg14 = 0.0d; 
			 
			double avg21 = 0.0d; 
			 
			double avg33 = 0.0d; 
			 
			double avg55 = 0.0d; 
			 
			double avg100 = 0.0d; 
			 
			double avg144 = 0.0d; 
			 
			double avg200 = 0.0d; 
			 
			 
			if(m3.size()== 3) 
			{ 
				 
				avg3 = m3.stream().mapToDouble(p-> p.getO()).average().getAsDouble(); 
				 
				m3.remove(gl.E_EMPTY); 
				 
			}else 
			{ 
				avg3 = a.getO(); 
			} 
			 
			if(m5.size()== 5) 
			{ 
				 
				avg5 = m5.stream().mapToDouble(p-> p.getO()).average().getAsDouble(); 
				 
				m5.remove(gl.E_EMPTY); 
				 
			}else 
			{ 
				avg5 = a.getO(); 
			} 
			 
			if(m7.size()== 7) 
			{ 
				avg7 = m7.stream().mapToDouble(p-> p.getO()).average().getAsDouble(); 
				 
				m7.remove(gl.E_EMPTY); 
				 
			}else 
			{ 
				avg7 = a.getO(); 
			} 
			 
			 
			if(m10.size()== 10) 
			{ 
				avg10 = m10.stream().mapToDouble(p-> p.getO()).average().getAsDouble(); 
				 
				m10.remove(gl.E_EMPTY); 
				 
			}else 
			{ 
				avg10 = a.getO(); 
			} 
			 
			if(m14.size()== 14) 
			{ 
				avg14 = m14.stream().mapToDouble(p-> p.getO()).average().getAsDouble(); 
				 
				m14.remove(gl.E_EMPTY); 
				 
			}else 
			{ 
				avg14 = a.getO(); 
			} 
			 
			if(m21.size()== 21) 
			{ 
				avg21 = m21.stream().mapToDouble(p-> p.getO()).average().getAsDouble(); 
				 
				m21.remove(gl.E_EMPTY); 
				 
			}else 
			{ 
				avg21 = a.getO(); 
			} 
			 
			if(m33.size()== 33) 
			{ 
				avg33 = m33.stream().mapToDouble(p-> p.getO()).average().getAsDouble(); 
				 
				m33.remove(gl.E_EMPTY); 
				 
			}else 
			{ 
				avg33 = a.getO(); 
			} 
			 
			if(m55.size()== 55) 
			{ 
				avg55 = m55.stream().mapToDouble(p-> p.getO()).average().getAsDouble(); 
				 
				m55.remove(gl.E_EMPTY); 
				 
			}else 
			{ 
				avg55 = a.getO(); 
			} 
			 
			if(m100.size()== 100) 
			{ 
				avg100 = m100.stream().mapToDouble(p-> p.getO()).average().getAsDouble(); 
				 
				m100.remove(gl.E_EMPTY); 
				 
			}else 
			{ 
				avg100 = a.getO(); 
			} 
			 
			if(m144.size()== 144) 
			{ 
				avg144 = m144.stream().mapToDouble(p-> p.getO()).average().getAsDouble(); 
				 
				m144.remove(gl.E_EMPTY); 
				 
			}else 
			{ 
				avg144 = a.getO(); 
			} 
			 
			if(m200.size()== 200) 
			{ 
				avg200 = m200.stream().mapToDouble(p-> p.getO()).average().getAsDouble(); 
				 
				m200.remove(gl.E_EMPTY); 
				 
			}else 
			{ 
				avg200 = a.getO(); 
			} 
			 
				TSbhl 	sbhl = TSbhl.getInstance(a.getSign(),a.getBody(),a.getHighShadow(),a.getLowShadow()); 
				 
				TMa 	ma = TMa.getInstance(avg3,avg5,avg7,avg10,avg14,avg21,avg33,avg55,avg100,avg144,avg200); 
				 
				TDays	dows = TDays.getInstance(a.getDate()); 
				 
				TCross	cross = TCross.getInstance(a.getDate()); 
				 
						cross.cmp(a.getO(),p_ma[0]); 
				 
				// Push into items. 
						 
				TPackageOne pko = new TPackageOne(a,sbhl,p_ma[0],dows,cross); 
						 
							this.items.add(pko); 
							 
				p_tbi[gl.E_EMPTY] 	= a; 
				 
				p_sbhl[gl.E_EMPTY] 	= sbhl; 
				 
				p_ma[gl.E_EMPTY] 	= ma; 

				p_dows[gl.E_EMPTY] 	= dows; 
				 
				p_cross[gl.E_EMPTY] = cross; 
				 
				 
				cnt[0] += 1; 
						 
		}); 
		 
				gl.tracex(new Object(){},String.format("Load REST...%s...rows...%06d","Ok",this.getItems().size())); 
		 
		 
				return true; 
	} 
	 
	public boolean loadPrimary(boolean from_file,String begin_date,String end_date) 
	{ 
		//start = System.nanoTime(); 
		 
		List<Bar> list = new ArrayList<Bar>(); 
		 
		String suiteName = "OHLC"; 
		 
		//long start = System.nanoTime(); 
		 
		boolean bl_result ; 
		 
		String[] series = {""}; 
		 
		if (!from_file) 
			bl_result = true;//DbUtils.getListOfItems(this.getTicker(),list); 
		else 
			 bl_result = Bar.readFromFile(String.format("%s\\%s.txt", 
					TConfiguration.DATA_DIR, 
					this.getTicker().toLowerCase()),list,series); 
			 
		 
		if (!bl_result) 
		{ 
			gl.tracex(new Object(){},String.format("Load primary set...%s","Error")); 
			 
			return bl_result; 
		} 
		 
			gl.tracex(new Object(){},String.format("Load primary set...%s...%s...rows...%06d",suiteName,"Ok",list.size())); 
		 
			list.forEach(a-> 
			{ 
					 
			TPackageOne pko = new TPackageOne(); 
			 
						pko.setTbi(a); 
						 
						items.add(pko); 
			 
			}); 
			 
			long end = System.nanoTime(); 
			 
			gl.tracex(new Object(){},String.format("Push primary set...%s...%s...rows...%06d...%06d ms.",suiteName,"Ok",items.size(),(end-start)/1000000)); 
			 
			return bl_result; 
		 
	} 
	 
	 
	public boolean loadPrimary(boolean from_db) 
	{ 
		List<Bar> list = new ArrayList<Bar>(); 
		 
		String[] series = {""}; 
		 
		String suiteName = "OHLC"; 
		 
		boolean bl_result  = false; 
		 
		if (from_db) 
			bl_result = true;//DbUtils.getListOfItems(this.getTicker(),list); 
		else 
			bl_result = Bar.readFromFile(String.format("%s\\%s.txt", 
					TConfiguration.DATA_DIR, 
					this.getTicker().toLowerCase()),list,series); 
			 
		 
		if (!bl_result) 
		{ 
			gl.tracex(new Object(){},String.format("Load primary set...%s","Error")); 
			 
			return bl_result; 
		} 
		 
			gl.tracex(new Object(){},String.format("Load primary set...%s...%s...rows...%06d",suiteName,"Ok",list.size())); 
		 
			list.forEach(a-> 
			{ 
					 
			TPackageOne pko = new TPackageOne(); 
						 
						pko.setTbi(a); 
						 
						items.add(pko); 
			 
			}); 
			 
			long end = System.nanoTime(); 
			 
			gl.tracex(new Object(){},String.format("Push primary set...%s...%s...rows...%06d...%06d ms.",suiteName,"Ok",items.size(),(end-start)/1000000)); 
			 
			return bl_result; 
		 
	} 
	 
	 
	public boolean bounds(List<TPackageOne> list) 
	{ 
		list.clear(); 
		 
		list.addAll(this.getItems()); 
		 
		int id[] = {0}; 
		 
		list.forEach(a->{a.setId(id[0]); id[0]++;}); 
		 
		return (list.size() == this.getItems().size()); 
	} 
	 
	@Override 
	public String toString() 
	{ 
		StringBuilder sb = new StringBuilder(); 
		 
		this.getItems().forEach(a-> 
		{ 
			sb.append(a.toString()); 
			 
			sb.append(System.lineSeparator()); 
		} 
		); 
		 
		return sb.toString(); 
	} 

	public String toStringTbi() 
	{ 
		StringBuilder sb = new StringBuilder(); 
		 
		this.getItems().forEach(a-> 
		{ 
			 
			sb.append(a.getTbi().toString()); 
			 
			sb.append(System.lineSeparator()); 
		} 
		); 
		 
		return sb.toString(); 
	} 

	 
	public static void OnLoad(String ticker,boolean from_file) 
	{ 
		TPackageOneManager pkom = TPackageOneManager.getInstance(ticker); 
		 
		if(!pkom.loadPrimary(from_file)) 
			gl.tracex(new Object(){},String.format("Operation [%s] completed with...%s","PPIMARY","Error")); 
		else 
			gl.tracex(new Object(){},String.format("Operation [%s] completed with...%s","PPIMARY","Ok")); 
		 
		if(!pkom.loadRest()) 
			gl.tracex(new Object(){},String.format("Operation [%s] completed with...%s","REST","Error")); 
		else 
			gl.tracex(new Object(){},String.format("Operation [%s] completed with...%s","REST","Ok")); 
		 
		if(!Fu.to_file(String.format("e:\\exp\\%s_pkg.txt",ticker),pkom.toString())) 
			gl.tracex(new Object(){},String.format("Operation [%s] completed with...%s","SAVE","Error")); 
		else 
			gl.tracex(new Object(){},String.format("Operation [%s] completed with...%s","SAVE","Ok")); 

		 
	} 

	public static void OnStatSuite(String cmd_file,boolean from_file) 
	{ 
		String[] tickers = Fu.getStringArrayFromFile(cmd_file); 
		 
		List<String> list = Arrays.asList(tickers); 
		 
		list.forEach(a->{OnStat(a,from_file);}); 
		 
	} 
	 
	 
	public static boolean getPackageOneAsList(String ticker, List<TPackageOne> items,boolean from_db) 
	{ 
		TPackageOneManager pkom = TPackageOneManager.getInstance(ticker); 
		 
		if(!pkom.loadPrimary(from_db) || !pkom.loadRest()) 
		{ 
				gl.tracex(new Object(){},String.format("Get items...%s","Error")); 
				 
				return false; 
		} 
				gl.tracex(new Object(){},String.format("Get items...%s","Ok")); 
		 
		if(!pkom.bounds(items)) 
		{ 
			gl.tracex(new Object(){},String.format("Bounds items...%s","Error")); 
			 
			return false; 
		} 
			gl.tracex(new Object(){},String.format("Bounds items...%s","Ok")); 
		 
		return true; 
	} 
	 
	public static boolean getPackageOneAsList(String ticker, List<TPackageOne> items,boolean from_file,String begin_date,String end_date) 
	{ 
		TPackageOneManager pkom = TPackageOneManager.getInstance(ticker); 
		 
		if(!pkom.loadPrimary(from_file) || !pkom.loadRest()) 
		{ 
				gl.tracex(new Object(){},String.format("Get items...%s","Error")); 
				 
				return false; 
		} 
				gl.tracex(new Object(){},String.format("Get items...%s","Ok")); 
		 
		if(!pkom.bounds(items)) 
		{ 
			gl.tracex(new Object(){},String.format("Bounds items...%s","Error")); 
			 
			return false; 
		} 
			gl.tracex(new Object(){},String.format("Bounds items...%s","Ok")); 
		 
		return true; 
	} 
	 
	public static void doMonthDistribution(List<TPackageOne> items) 
	{ 
		Map<Integer,TDowDistribution> map = new LinkedHashMap<Integer,TDowDistribution>(); 
	 
		 
		 
		 
	} 
	 
	 
	public static void doDowDistribution(List<TPackageOne> items) 
	{ 
		 
		final int  MON=2,TUE=3,WED=4,THU=5,FRI=6; 
		 
		final int  MONR=0,TUER=1,WEDR=2,THUR=3,FRIR=4; 
		 
		int[] dw = {0,0,0,0,0}; 
		 
		int[] db = {0,0,0,0,0}; 
		 
		int[] dz = {0,0,0,0,0}; 
					 
		items.forEach(a-> 
		{ 
						 
			switch(a.getSbhl().getSign()) 
			{ 
				case 1: 
				{ 
					switch(a.getDays().getDow()) 
					{ 
						case MON: 
							{ 
							dw[MONR]++; 
							} 
							break; 
						case TUE: 
							dw[TUER]++; 
							break; 
						case WED: 
							dw[WEDR]++; 
							break; 
						case THU: 
							dw[THUR]++; 
							break; 
						case FRI: 
							dw[FRIR]++; 
							break; 
					} 
					 
				} 
				break; 
				 
				case 0: 
				{ 
					switch(a.getDays().getDow()) 
					{ 
						case MON: 
							dz[MONR]++; 
							break; 
						case TUE: 
							dz[TUER]++; 
							break; 
						case WED: 
							dz[WEDR]++; 
							break; 
						case THU: 
							dz[THUR]++; 
							break; 
						case FRI: 
							dz[FRIR]++; 
							break; 
					} 
				} 
				break; 
				 
				case -1: 
				{ 
					switch(a.getDays().getDow()) 
					{ 
						case MON: 
							db[MONR]++; 
							break; 
						case TUE: 
							db[TUER]++; 
							break; 
						case WED: 
							db[WEDR]++; 
							break; 
						case THU: 
							db[THUR]++; 
							break; 
						case FRI: 
							db[FRIR]++; 
							break; 
					} 
					 
				} 
				break; 
				 
			} 

		} 
		); 
		 
		for(int i=MONR;i<FRIR+1;i++) 
		{ 
			String msg = String.format("Day : %01d %04d %04d %04d",i+2,dw[i],db[i],dz[i]); 
					 
			gl.smn(msg); 
		} 
		 
		// Summary. 
		int s_dw=0,s_db=0,s_dz=0; 
		 
		for(int i=MONR;i<FRIR+1;i++) 
		{ 
			s_dw += dw[i]; 
			 
			s_db += db[i]; 
			 
			s_dz += dz[i]; 
					 
		} 
		 
		String msg = String.format("Sum :   %04d %04d %04d %05d ",s_dw,s_db,s_dz,(s_dw+s_db+s_dz)); 
		 
		gl.smn(msg); 
		 
	} 
	 
	public static void doDayOfMonthDistrib(List<TPackageOne> items) 
	{ 
		 
		Map<Integer,TDowDistribution> mp = new LinkedHashMap<Integer,TDowDistribution>(); 
		 
		// Init map. 
		 
		for(int i=1;i<32;i++) 
		{ 
			TDowDistribution tdd = TDowDistribution.getInstance(); 
			 
				tdd.setDow(i); 
			 
				mp.put(i,tdd); 
		} 
			 
		items.forEach(a-> 
		{ 
		 
			switch(a.getSbhl().getSign()) 
			{ 
				case 1: 
				{ 
					MapUtils.findValueByKey(mp,DateUtil.day_of_month(a.getTbi().getDate())).incWhite(); 
					 
				} 
				break; 
				 
				case 0: 
				{ 
					MapUtils.findValueByKey(mp,DateUtil.day_of_month(a.getTbi().getDate())).incZero(); 
				} 
				break; 
				 
				case -1: 
				{ 
					MapUtils.findValueByKey(mp,DateUtil.day_of_month(a.getTbi().getDate())).incBlack(); 
				} 
				break; 
				 
			} 

		} 
		); 
		 
		for(Entry<Integer,TDowDistribution> e : mp.entrySet()) 
		{ 
			//gl.smn("" + e.getKey() + " " + e.getValue().toString()); 
			 
			String msg = String.format("%s",e.getValue().toString()); 
			 
			gl.smn(msg); 
		} 
	} 

		 
	public static List<TCrossMetric> getCrossMetricPackage(List<TPackageOne> items) 
	{ 
		List<TCrossMetric> list = new ArrayList<TCrossMetric>(); 
		 
		TCross[] 	p_cross = { null }; 
		 
		items.forEach(a-> 
			{ 
				TCrossMetric.getInstances(p_cross[0],a.getCross(),list); 
						 
				p_cross[0] = a.getCross(); 
			} 
		); 
		 
				return list; 
	} 
	 
	public static boolean  createCrossMetricBase(List<TPackageOne> source_items,List<TCrossMetric> target_items) 
	{ 
		TCross[] 	p_cross = { null }; 
		 
		try 
		{ 
			source_items.forEach(a-> 
				{ 
					TCrossMetric.getInstances(p_cross[0],a.getCross(),target_items); 
							 
					p_cross[0] = a.getCross(); 
				} 
			); 
		} 
		catch(Exception e) 
		{ 
			return false; 
		} 
		 
		boolean bl_result = !(target_items.size() == gl.E_EMPTY); 
		 
		if(!bl_result) 
		gl.tracex(new Object(){},String.format("Collection is empty...%s","Error")); 
		else 
		gl.tracex(new Object(){},String.format("Collection size...%d",target_items.size())); 
			 
		return bl_result; 
			 
	} 
	 
	 
	 
	 
	public static boolean doDowTypeDistribSuite(String ticker,boolean from_file) 
	{ 
		 
		//start = System.nanoTime(); 
		 
		List<TPackageOne> items = new ArrayList<TPackageOne>(); 
		 
		if(!getPayload(ticker,from_file,items)) 
			return true; 
		 
		 
		int[] dow = {0,0}; 
		 
		int[] ma = {0,0}; 
		 
		StringBuilder sb = new StringBuilder(); 
		 
		// Header. 
		sb.append(String.format("%s","  ")); 
		 
		//for( ma[0] = CandleUtil.NOP;ma[0] < CandleUtil.NONE;ma[0]++) 
		CandleUtil.types.forEach(t->
		{
			sb.append(String.format("%s ",gl.format(CandleUtil.getCTypeOf(ma[0]),gl.AL.LEFT,4))); 
		}); 
		
			gl.smn(sb.toString()); 
		 
			sb.setLength(gl.E_EMPTY); 
		 
		for(dow[0] = DateUtil.MONDAY;dow[0] <= DateUtil.FRIDAY;dow[0]++) 
		{ 
				sb.append(String.format("%01d",dow[0])); 
			 
				CandleUtil.types.forEach(t->
				{
				long value = items.stream().filter( 
						(a-> ( 
								a.getTbi().getType() == ma[0] && 
								 
								DateUtil.day_of_week(a.getTbi().getDate())== dow[0] 
						)) 
						).count(); 
				 
				sb.append(String.format(" %04d",value)); 
				 
			}); // for type. 
			 
			// Last column. 
			 
				long val = items.stream().filter( 
						(a-> ( 
								DateUtil.day_of_week(a.getTbi().getDate())== dow[0] 
						)) 
						).count(); 
				 
				sb.append(String.format(" %04d",val)); 
				 
				gl.smn(sb.toString()); 
				 
				sb.setLength(gl.E_EMPTY); 
		 
		}// for dow 
		 
		 
		// Last row. 
		sb.append(String.format("%s"," ")); 
		 
		CandleUtil.types.forEach(t->
		{
			long value = items.stream().filter( 
					(a-> ( 
							a.getTbi().getType() == ma[0] 
					)) 
					).count(); 
			 
			sb.append(String.format(" %04d",value)); 
			 
		}); // for type. 
		 
			gl.smn(sb.toString()); 
		 
			sb.setLength(gl.E_EMPTY); 
			 
			long count = items.stream().filter( 
					(a-> ( 
							a.getTbi().getType() != gl.E_EMPTY 
					)) 
					).count(); 
			; 
			 
			gl.tracex(new Object(){},String.format("Collection size...%05d",count)); 

		 
			return true; 
		 
	} 

	public static boolean rawCrossMetricSuite(String ticker,boolean from_file) 
	{ 
		 
		//start = System.nanoTime(); 
		 
		List<TPackageOne> items = new ArrayList<TPackageOne>(); 
		 
		if(!getPayload(ticker,from_file,items)) 
			return true; 
		 
		List<TCrossMetric> list = getCrossMetricPackage(items); 
		 
		long  all 	= list.stream().mapToInt(a->a.getType()).count(); 
		 
		long  up 	= list.stream().mapToInt(a->a.getType()).filter(b->(b==TCross.UP)).count(); 
		 
		long  down 	= list.stream().mapToInt(a->a.getType()).filter(b->(b==TCross.DOWN)).count(); 
			 
		long  m3_up = list.stream().filter( 
				(a-> (a.getTarget()== TCrossMetric.M3 && a.getType()==TCross.UP)) 
				).count(); 
		 
		long m3_down = list.stream().filter( 
				(a-> (a.getTarget()== TCrossMetric.M3 && a.getType()==TCross.DOWN)) 
				).count(); 

		long m3_up_monday = list.stream().filter( 
				(a-> ( 
						a.getTarget()== TCrossMetric.M3 && 
						a.getType()==TCross.UP && 
						DateUtil.day_of_week(a.getBegin())== DateUtil.MONDAY 
						 
				)) 
				).count(); 

		long m3_down_monday = list.stream().filter( 
				(a-> ( 
						a.getTarget()== TCrossMetric.M3 && 
						a.getType()==TCross.DOWN && 
						DateUtil.day_of_week(a.getBegin())== DateUtil.MONDAY 
				)) 
				).count(); 
		 
		gl.smn(String.format("All items : %05d Up : %04d Down : %04d CheckSum : %05d  Re-Up: %05d Re-Down: %05d Monday-Up: %05d Monday-Down: %05d", 
				all,up,down,(up+down),m3_up,m3_down, 
				m3_up_monday,m3_down_monday 
				)); 
		 
				return true; 
				 
	} 
	// What about average durability of the crosses. 
		 
	public static boolean doCrossMetricDurabilitySuite(String ticker,boolean from_file) 
	{ 
		 
		//start = System.nanoTime(); 
		 
		List<TPackageOne> items = new ArrayList<TPackageOne>(); 
		 
		if(!getPayload(ticker,from_file,items)) 
			return true; 
		 
		List<TCrossMetric> list = new ArrayList<TCrossMetric>(); 
				 
		if(!createCrossMetricBase(items,list)) 
			return false; 
		 
		int[] dow = {0,0}; 
		 
		int[] ma = {0,0}; 
		 
		list.forEach(a->{ 
			 
			TCrossMetric cm = 
					list.stream().filter( 
							(b-> ( 
									b.getTarget()== a.getTarget() && 
									b.getType()== ((a.getType()== TCross.UP ) ? TCross.DOWN : TCross.UP) && 
									DateUtil.days_between(a.getBegin(),b.getBegin()) > 3 
							))) 
							.findFirst() 
							.orElse(null); 
			 
			if(cm != null) 
			{ 
				a.setEnd(cm.getBegin()); 
				 
				a.setDurability(DateUtil.days_between(a.getBegin(),cm.getBegin())); 
			} 
							 
		}); 
		 
		list.forEach(c-> 
		{ 
			if(c.getDurability() > 3) 
			{ 
				gl.smn(c.toString()); 
				 
				ma[0]++; 
			} 
		} 
		); 
		 
		gl.smn(String.format("Count of useful items : %05d",ma[0])); 
		 
		return true; 
				 
	} 

	 
	 
	public static boolean getCrossMetricFullPackage_(String ticker,boolean from_file) 
	{ 
		 
		//start = System.nanoTime(); 
		 
		List<TPackageOne> items = new ArrayList<TPackageOne>(); 
		 
		if(!getPayload(ticker,from_file,items)) 
			return true; 
		 
		List<TCrossMetric> list = new ArrayList<TCrossMetric>(); 
				 
		if(!createCrossMetricBase(items,list)) 
			return false; 
		 
	 
		int[] ma = {0,0}; 
		 
		list.forEach(a->{ 
			 
			 
			TCrossMetric cm = 
					list.stream().filter( 
							(b-> ( 
									b.getTarget()== a.getTarget() && 
									b.getType()== ((a.getType()== TCross.UP ) ? TCross.DOWN : TCross.UP) && 
									b.getBegin().after(a.getBegin()) 
							))) 
							.findFirst() 
							.orElse(null); 
				 
			if(cm != null ) 
			{ 
				a.setEnd(cm.getBegin()); 
				 
				a.setDurability(DateUtil.days_between(a.getBegin(),cm.getBegin())); 
				 
			} 
			 
		}); 
		 
		list.forEach(c-> 
		{ 
			if( c.getBegin().before(DateUtil.to_date("31.01.1974")) && c.getDurability() > 2) 
			{ 
				gl.smn(c.toString()); 
				 
				ma[0]++; 
			} 
		} 
		); 
		 
				gl.smn(String.format("Count of useful items : %05d",ma[0])); 
		 
				return true; 
				 
	} 

	 
	public static boolean getCrossMetricFullPackage(String ticker,boolean from_file,String begin_date,String end_date) 
	{ 
		 
		//start = System.nanoTime(); 
		 
		List<TPackageOne> items = new ArrayList<TPackageOne>(); 
		 
		if(!getPayload(ticker,from_file,items)) 
			return true; 
		 
		List<TCrossMetric> list = new ArrayList<TCrossMetric>(); 
				 
		if(!createCrossMetricBase(items,list)) 
			return false; 
		 
	 
		int[] ma = {0,0}; 
		 
		list.forEach(a->{ 
			 
			 
			TCrossMetric cm = 
					list.stream().filter( 
							(b-> ( 
									b.getTarget()== a.getTarget() && 
									b.getType()== ((a.getType()== TCross.UP ) ? TCross.DOWN : TCross.UP) && 
									b.getBegin().after(a.getBegin()) 
							))) 
							.findFirst() 
							.orElse(null); 
				 
			if(cm != null ) 
			{ 
				a.setEnd(cm.getBegin()); 
				 
				a.setDurability(DateUtil.days_between(a.getBegin(),cm.getBegin())); 
				 
			} 
			 
		}); 

		Date dt_begin = DateUtil.to_date(begin_date); 
		 
		Date dt_end = DateUtil.to_date(end_date); 
		 
		list.forEach(c-> 
		{ 
			 
			if( c.getBegin().before(dt_end) && c.getBegin().after(dt_begin) && c.getDurability() > 2) 
			{ 
				gl.smn(c.toString()); 
				 
				ma[0]++; 
			} 
		} 
		); 
		 
				gl.smn(String.format("Count of useful items : %05d",ma[0])); 
		 
				return true; 
				 
	} 

	public static void showOrders(List<TOrder> list,Range range,boolean to_file) 
	{ 

		int[] ma = {0,0}; 
		 
		StringBuilder sb = new StringBuilder(); 
		 
		sb.append(System.lineSeparator()); 
		 
		if(list.size()== gl.E_EMPTY) 
		{ 
			gl.tracex(new Object(){},String.format("Orders collection is empty...%s\n",gl.S_ERROR));			 
			 
			return ; 
		} 
			 
		String ticker = list.get(gl.E_EMPTY).getTicker(); 
		 
		list.forEach(c-> 
		{ 
			 
			if( c.getOpen().before(range.getDate_to()) && c.getOpen().after(range.getDate_from())) 
			{ 
				 
				sb.append(c.toString()); 
				 
				sb.append(System.lineSeparator()); 
				 
				ma[0]++; 
				 
			} 
		} 
		); 
		 
			 
		TOrderStat tos = TOrderStat.getInstance(list); 
		 
		TConfiguration.ratings.add(tos); 
		 
		String msg = tos.toString(); 
		 
		sb.append(System.lineSeparator()); 
		 
		sb.append(msg); 
		 
		sb.append(System.lineSeparator()); 
		 
		gl.tracex(new Object(){},msg); 
		 
		 
		if(to_file) 
		{ 
		 	String fileExport = Fu.getInjectedFileName(TConfiguration.ORDERS_REPORT_FILE, ticker, range); 
			 
			if(!Fu.saveStringToFile(fileExport,sb.toString(),TConfiguration.REPORT_FILE_APPEND_MODE,true)) 
			{ 
				gl.tracex(new Object(){},String.format("Error while write to file...%s...%s\n",fileExport)); 
			} 
		} 
		else 
			gl.smn(sb.toString()); 
		 
			 
	} 
	 
	 

	public static void setPeriod(String begin_date,String end_date) 
	{ 
		TConfiguration.PERIOD_BEGIN = begin_date; 
		 
		TConfiguration.PERIOD_END   = end_date; 
		 
	} 
	 

	 
	public static boolean doOrdersSuiteList(List<TPortfolioStat> portfolio,List<TPackageOne>  pkos,List<Integer> initiators ,List<Integer> closers, String ticker,Range range ,boolean to_file) 
	{ 
		 
		if(initiators.size() == gl.E_EMPTY || closers.size()== gl.E_EMPTY) 
		{ 
				gl.tx_e(new Object(){} ,"Input colletion is empty"); 
			 
				return false; 
		} 
		 
				 
			initiators.forEach(a-> 
			{ 
			 
				closers.forEach(b-> { 
					 
				doOrdersSuiteList(portfolio,pkos,a,b,ticker,range,to_file); 
				 
				}); // closers 
				 
			}); // initiators 
			 
			 
				return true; 
		 
	} 
	 
	 
	 
 
	public static TDoublePair getDealCloseDateByHighSuite(List<TPackageOne> items,Range range) 
	{ 
		List<TPackageOne> filter = new ArrayList<TPackageOne>(); 
		 
		if(!TPackageOneManager.acceptFilter(items,filter,range)) 
			return null; 
		 
		return getDealCloseDateByHigh(filter); 
		 
	} 
	 
	public static TDoublePair getDealCloseDateByLowSuite(List<TPackageOne> items,Range range) 
	{ 
		List<TPackageOne> filter = new ArrayList<TPackageOne>(); 
		 
		if(!TPackageOneManager.acceptFilter(items,filter,range)) 
			return null; 
		 
		return getDealCloseDateByLow(filter); 
		 
	} 
	 
	 
	public static TDoublePair getDealCloseDateByHigh(List<TPackageOne> items) 
	{ 
		 
		TDoublePair dp = new TDoublePair(); 
				 
		Date dt[] = {items.get(gl.E_EMPTY).getTbi().getDate()}; 
		 
		double open[] = {items.get(gl.E_EMPTY).getTbi().getO()}; 
		 
		double high[] = {open[0]}; 
		 
		double high_profit[] = {0.0}; 
		 
		boolean bl_stop[] = {false}; 
		 
		boolean bl_high[] = {false}; 
		 
		int cnt[] = {0}; 
		 
		 
		items.forEach(a->{ 
			 
			if (a.getTbi().getH() > high[0] && !bl_stop[0]) 
			{ 
				high[0] = a.getTbi().getH(); 
				 
				high_profit[0] = (high[0] - open[0]); 
						 
				bl_high[0] = true; 
				 
				dp.setDate(a.getTbi().getDate()); 
				 
				dp.setClose(high[0]); 
				 
			 
			} 
			 
			// First time down shift. 
			 
			if ( a.getTbi().getH() < high[0] && bl_stop[0] == false && cnt[0] > 1) 
					//&& DateUtil.getDaysBetween(dp.getDate(),a.getTbi().getDate()) >= 2) 
			{ 
				// Get absolute value of shift to down. 
				 
				double delta = (a.getTbi().getH()  - open[0]); 
				 
				double sigma = (delta / high_profit[0]); 
				 
				// gl.smn("Sigma : " + (sigma)); 
				 
				if(sigma <=  0.95  || sigma >= 0.96) 
				{ 
					dt[0] = a.getTbi().getDate(); 
					 
					dp.setDate(dt[0]); 
					 
					dp.setClose(a.getTbi().getH()); 
										 
					bl_stop[0] = true; 
					 
					// gl.smn("Catch : " + (delta) + " Profit : " + high_profit[0] + " Sigma : " + sigma); 
					 
				} 
				 
			} // down 
			 
				cnt[0]++; 
			 
		}); 
			 
				return dp; 
	} 
	 
	 

	public static TDoublePair getDealCloseDateByLow(List<TPackageOne> items) 
	{ 
		 
		TDoublePair dp = new TDoublePair(); 
				 
		Date dt[] = {items.get(gl.E_EMPTY).getTbi().getDate()}; 
		 
		double open[] = {items.get(gl.E_EMPTY).getTbi().getO()}; 
		 
		double high[] = {open[0]}; 
		 
		double high_profit[] = {0.0}; 
		 
		boolean bl_stop[] = {false}; 
		 
		boolean bl_high[] = {false}; 
		 
		int cnt[] = {0}; 
		 
		 
		items.forEach(a->{ 
			 
			if (a.getTbi().getL() < high[0] && !bl_stop[0]) 
			{ 
				high[0] = a.getTbi().getL(); 
				 
				high_profit[0] = (open[0] - high[0] ); 
						 
				bl_high[0] = true; 
				 
				dp.setDate(a.getTbi().getDate()); 
				 
				dp.setClose(high[0]); 
				 
				//cnt[0]++; 
			} 
			 
			// First time down shift. 
			 
			if ( a.getTbi().getL() > high[0] && bl_stop[0] == false  && cnt[0] > 1) 
			{ 
				// Get absolute value of shift to down. 
				 
				double delta = (open[0] - a.getTbi().getL() ); 
				 
				if(high_profit[0] == 0.0) 
					high_profit[0] = 1.0; 
				 
				double sigma = (delta / high_profit[0]); 
				 
				//gl.smn("Sigma : " + (sigma)); 
				 
				if(sigma <=  0.95  || sigma >= 0.96) 
				{ 
					dt[0] = a.getTbi().getDate(); 
					 
					dp.setDate(dt[0]); 
					 
					dp.setClose(a.getTbi().getL()); 
										 
					bl_stop[0] = true; 
					 
					//gl.smn("Catch : " + (delta) + " Profit : " + high_profit[0] + " Sigma : " + sigma); 
					 
				} 
				 
			} // down 
			 
			cnt[0]++; 
			 
		}); 
			 
				return dp; 
	} 
	public static TDoublePair getHighLow(List<TPackageOne> pko_items,Range range) 
	{ 
		List<TPackageOne> filter = new ArrayList<TPackageOne>(); 
		 
		acceptFilter(pko_items,filter,range); 
		 
		TDoublePair dp = new TDoublePair(); 
		 
		// Init by first value; 
		 
		if(filter.size() == gl.E_EMPTY) 
			return  dp; 
		 
		dp.setHigh(filter.get(gl.E_EMPTY).getTbi().getO()); 
		 
		dp.setLow(dp.getHigh()); 
							 
		filter.forEach(a-> 
		{ 
				if(a.getTbi().getH() > dp.getHigh()) 
					dp.setHigh(a.getTbi().getH()); 
			 
				if(a.getTbi().getL() < dp.getLow()) 
					dp.setLow(a.getTbi().getL()); 
									 
		} 
		); 
		 
				return dp; 
	} 
		 
	 
	public static boolean doOrdersSuiteList(List<TPortfolioStat> portfolio,List<TPackageOne>  pkos,int initiator , int closer, String ticker,Range range,boolean to_file) 
	{ 

	 
		TConfiguration.ratings.clear(); 
		 
		List<TOrder> orders = new ArrayList<TOrder>(); 
		 
		doOrdersSuite(pkos,orders,initiator,closer,ticker,range,to_file); 
			 
		showOrders(orders,range,to_file); 
			 
		orders.clear(); 
				 
	 
	// Just type 
	TPortfolioStat po = new TPortfolioStat(ticker, 
				 initiator, 
				 closer, 
				 range, 
				 TPortfolioStat.getPortfolioResult()); 
		 
		 
		 portfolio.add(po); 
		 
		 
		// The summary section. 
	 
		StringBuilder sb = new StringBuilder(); 
		 
		String sortedByResult = TOrderStat.toString(TSorter.getSortedByResult(false)); 
	 
	    sb.append(System.lineSeparator()); 
		 
	    sb.append(String.format("%d-%d %s %s",initiator,closer,range.toString(),gl.fmt2(TPortfolioStat.getPortfolioResult()))); 
	 
	    sb.append(System.lineSeparator()); 
	 
	    sb.append(sortedByResult); 
	 
	  	gl.smn(sb.toString()); 
		 
		// Consolidation file info. 
		 
		 
		if(to_file) 
		{ 

			String fileExport = Fu.getInjectedFileName(TConfiguration.ORDERS_REPORT_FILE,"group", range); 
		 
			if(!Fu.saveStringToFile(fileExport,sb.toString(),TConfiguration.REPORT_FILE_APPEND_MODE,true)) 
			{ 
				gl.tracex(new Object(){},String.format("Error while write to file...%s...%s\n",fileExport,gl.S_ERROR)); 
				 
				return false; 
			} 
			else 
				gl.tracex(new Object(){},String.format("Write to file...%s...%s\n",fileExport,gl.S_OK)); 
			 
		} 
		 
		 
			sb.setLength(gl.E_EMPTY); 
	 
			return true; 
	} 
	 
	 
	 
	public static boolean createOrders(List<TPackageOne>  pko_items,List<TCrossMetric> cross_items, List<TOrder> orders, int initiator , int closer, String ticker) 
	{ 
		 
		if(cross_items == null || cross_items.size() == gl.E_EMPTY) 
		{ 
			gl.tx_e(new Object(){},"Empty input colletion "); 
			 
			return false; 
		} 
		 
		 
		cross_items.forEach(a->{ 
			 
			// Get target date. 
			 
			 
			TPackageOne po_skip = pko_items.stream().filter( 
					(b-> ( b.getTbi().getDate().equals(a.getBegin()) 
					))) 
					.findFirst() 
					.orElse(null); 
			 
			 
			int find_index = po_skip.getId()+ TConfiguration.SKIP_DAYS_BEFORE_OPEN_THE_DEAL; 
			 
			if(find_index <= pko_items.get(pko_items.size()-1).getId()) 
			{ 
			   int skip_date_id =  find_index; 
			 
			   Date order_date_open = TPackageOne.getPkoById(pko_items,skip_date_id).getTbi().getDate(); 
			 
			TPackageOne po_begin = pko_items.stream().filter( 
					(b-> ( b.getTbi().getDate().equals(order_date_open) 
					))) 
					.findFirst() 
					.orElse(null); 
		 
			TPackageOne po_end = pko_items.stream().filter( 
					(b-> ( b.getTbi().getDate().equals(a.getEnd()) 
					))) 
					.findFirst() 
					.orElse(null); 
			 
		 		 
				if( po_begin != null && po_end != null && po_skip != null && skip_date_id != 0) 
				{ 
					int[] type = {gl.E_ERROR}; 
							 
					if ( a.getType() == TCross.UP) 
						type[0] = TOrder.BUY_MARKET; 
					else if ( a.getType() == TCross.DOWN) 
						type[0] = TOrder.SELL_MARKET; 

		 
				if(a.getTarget() == initiator) 
				  { 
					 
				 	TDoublePair high_low = getHighLow(pko_items,new Range( 
				  			po_begin.getTbi().getDate(), 
				  			po_end.getTbi().getDate() 
				  			) 
				 	); 
				 	 
				 					 	 
				 	TDoublePair rt = new TDoublePair(); 
				 	 
				 	Range dr = new Range(po_begin.getTbi().getDate(),po_end.getTbi().getDate()); 
				 	 
				 	if (TConfiguration.VERSION.equalsIgnoreCase("v1")) 
				 	{ 
					    if (type[0] == TOrder.BUY_MARKET) 
					    	rt = TPackageOneManager.getDealCloseDateByHighSuite(pko_items,dr); 
					    else if (type[0] == TOrder.SELL_MARKET) 
					    	rt = TPackageOneManager.getDealCloseDateByLowSuite(pko_items,dr); 
				 	} 
				 	else if (TConfiguration.VERSION.equalsIgnoreCase("raw")) 
				 	{ 
				 		rt.setDate(po_end.getTbi().getDate()); 
				 		 
				 		rt.setClose(po_end.getTbi().getO()); 
				 	} 
				 		 
				 
				     //Range dr1 = new Range(po_begin.getTbi().getDate(),rt.getDate()); 
				 
				     //gl.smn("Range : " + dr1.toString()); 
				 
					  TOrder order = TOrder.getInstance(ticker, 
						  orders.size()+1, 
						  type[0], 
						  a.getTarget(), 
						  po_begin.getTbi().getDate(), 
						  rt.getDate(), 
						  po_begin.getTbi().getO(), 
						  rt.getClose(), 
						  high_low 
						  ); 
				 
					  	orders.add(order); 
					  	 
					  	//gl.smn("Drop down value : " + gl.format4d(drop_down,gl.AL.RIGHT,8)); 
				 
				  } // if(a.getTarget() == TConfiguration.OPEN_ORDER_TYPE) 
			//}); 
			 
				} // if( po_begin != null && po_end != null) 
				 
			} // if find_index in the range. 
		}); 
	 
				 
				if(orders.size()== gl.E_EMPTY) 
				{ 
				 
					gl.tracex(new Object(){},String.format("Orders collection is empty...%s",gl.S_ERROR)); 
					 
					return false; 
				} 
					gl.tracex(new Object(){},String.format("Count of orders : %05d",orders.size())); 
 
					return true; 
				 
	} 

	 
	public static boolean doOrdersSuite(List<TPackageOne>  items,List<TOrder> orders , int initiator , int closer, String ticker,Range range,boolean to_file) 
	{ 
			 
		List<TCrossMetric> list = new ArrayList<TCrossMetric>(); 
		 
		if (!createCrossMetric(closer,items,list)) 
			return false; 

		if(!createOrders(items,list,orders,initiator,closer,ticker)) 
			return false; 
		 
			return true; 
		 
	} 

	public  static boolean getOrdersPackageByCrossMetricListAndRange(String ticker,List<TPackageOne>  items,List<TCrossMetric> list,List<TOrder> orders,TRange range) 
	{ 
		orders.clear(); 
		 
		list.forEach(a->{ 
			 
			TPackageOne po_begin = items.stream().filter( 
					(b-> ( b.getTbi().getDate().equals(a.getBegin()) 
					))) 
					.findFirst() 
					.orElse(null); 
			 
			 
			TPackageOne po_end = items.stream().filter( 
					(b-> ( b.getTbi().getDate().equals(a.getEnd()) 
					))) 
					.findFirst() 
					.orElse(null); 
		 	 
			 
			 
				if( po_begin != null && po_end != null) 
				{ 
					int type = gl.E_ERROR; 
							 
					if ( a.getType() == TCross.UP) 
						type = TOrder.BUY_MARKET; 
					else if ( a.getType() == TCross.DOWN) 
						type = TOrder.SELL_MARKET; 
				 
					 
					TDoublePair drop_down = getHighLow(items,new Range( 
				  			po_begin.getTbi().getDate(), 
				  			po_end.getTbi().getDate()) 
					); 
				 
					 
				  TOrder order = TOrder.getInstance( 
						  ticker, 
						  orders.size()+1, 
						  type, 
						  a.getTarget(), 
						  po_begin.getTbi().getDate(), 
						  po_end.getTbi().getDate(), 
						  po_begin.getTbi().getO(), 
						  po_end.getTbi().getO(), 
						  drop_down 
						  ); 
				 
				  long days_between = DateUtil.days_between( 
						  po_begin.getTbi().getDate(), 
						  po_end.getTbi().getDate() 
						  ); 
				 
				  if(range.in(days_between)) 
				     orders.add(order); 
					 
				} 
		}); 
		 
				 
		return (orders.size() > gl.E_EMPTY); 
		 
	} 
	 
	public  static boolean getOrdersPackageByCrossMetricListAndRangeEx(String ticker,List<TPackageOne>  items,List<TCrossMetric> list,List<TOrder> orders,TRange range) 
	{ 
		orders.clear(); 
		 
		long begin = System.nanoTime(); 
		 
		list.forEach(a->{ 
			 
			double begin_rate = TPackageOne.getOByDate(items,a.getBegin()); 
			 
			double end_rate = TPackageOne.getOByDate(items,a.getEnd()); 
			 
				if( begin_rate != 0.0 && end_rate != 0.0) 
				{ 
					int type = gl.E_ERROR; 
							 
					if ( a.getType() == TCross.UP) 
						type = TOrder.BUY_MARKET; 
					else if ( a.getType() == TCross.DOWN) 
						type = TOrder.SELL_MARKET; 
				 
					TDoublePair high_low = getHighLow(items,new Range( 
				  			a.getBegin(), 
				  			a.getEnd() 
				  			)); 
				 
					  TOrder order = TOrder.getInstance( 
						  ticker, 
						  orders.size()+1, 
						  type, 
						  a.getTarget(), 
						  a.getBegin(), 
						  a.getEnd(), 
						  begin_rate, 
						  end_rate, 
						  high_low 
						  ); 
				 
				  long days_between = DateUtil.days_between( 
						  a.getBegin(), 
						  a.getEnd() 
						  ); 
				 
				  if(range.in(days_between)) 
				     orders.add(order); 
					 
				} 
		}); 
		 
		 
		return (orders.size() > gl.E_EMPTY); 
		 
	} 
 
	public  static boolean showOrdersPackageStat(String ticker,List<TOrder> orders,TRange range,String begin_date,String end_date) 
	{ 
		return writeOrdersPackageStat(ticker,orders,range,begin_date,end_date,false); 
	} 
	 
	 
	public  static boolean writeOrdersPackageStat(String ticker,List<TOrder> orders,TRange range,String begin_date,String end_date,boolean to_file) 
	{ 

		if(orders == null /*|| orders.size()== gl.E_EMPTY*/) 
		{ 
			gl.tracex(new Object(){},String.format("Input collection is empty...%s",gl.S_ERROR)); 
			 
			return false; 
		} 
		 
		 
		Date dt_begin = DateUtil.to_date(begin_date); 
		 
		Date dt_end = DateUtil.to_date(end_date); 
		 
		final int BUY_PROFIT= 0, 
			BUY_LOSE 		= 1, 
			BUY_RESULT 		= 2, 
			BUY_PROFIT_CNT 	= 3, 
			BUY_LOSE_CNT 	= 4, 
			BUY_RESULT_CNT 	= 5, 
			SELL_PROFIT 	= 0, 
			SELL_LOSE 		= 1, 
		 	SELL_RESULT 	= 2, 
		 	SELL_PROFIT_CNT = 3, 
		 	SELL_LOSE_CNT 	= 4, 
		 	SELL_RESULT_CNT = 5; 
		 
		double s[] = {0.0f,0.0f,0.0f,0.0f,0.0f,0.0f}; 
		 
		double b[] = {0.0f,0.0f,0.0f,0.0f,0.0f,0.0f}; 
		 
		// Get average rate for calculate 
		 
		double[] last_open_rate = {0.0}; 
		 
		orders.forEach(a-> 
		{ 
			if (a.getOpen().before(dt_end) && a.getOpen().after(dt_begin)) 
			{ 
				 
			 
			if(a.getType() == TOrder.SELL_MARKET) 
			{ 
				if(a.getProfit() > 0.0f) 
				{ 
					s[SELL_PROFIT] += a.getProfit(); 
					 
					s[SELL_PROFIT_CNT]++; 
				} 
				else if(a.getProfit() < 0.0f) 
				{ 
					s[SELL_LOSE] += a.getProfit(); 
					 
					s[SELL_LOSE_CNT]++; 
				} 
					 
					s[SELL_RESULT] += a.getProfit(); 
					 
					s[SELL_RESULT_CNT]++; 
					 
			} else if(a.getType() == TOrder.BUY_MARKET ) 
			{ 
				if(a.getProfit() > 0.0f) 
				{ 
					b[BUY_PROFIT] += a.getProfit(); 
					 
					b[BUY_PROFIT_CNT]++; 
				} 
				else if(a.getProfit() < 0.0f) 
				{ 
					b[BUY_LOSE] += a.getProfit(); 
					 
					b[BUY_LOSE_CNT]++; 
					 
				} 
					 
					b[BUY_RESULT] 	  += a.getProfit(); 
					 
					b[BUY_RESULT_CNT]++; 
			} 
			 
			} //if by date. 
			 
			last_open_rate[0] = a.getRate_close(); 
		} 
		); 
		 
			 
		String LM = TConfiguration.REPORT_COLUMN_DELIMITER; 
		 
		String msg = String.format("%s%s%2d%s" 
				+ "%s%s%s%s%s%s%s%s%s%s%s%s" 
				+ "%5d%s%5d%s%5d%s%5d%s%5d%s%5d%s%s%s%s%s%s ", 
				 gl.format(ticker.trim(),gl.AL.LEFT,5), 
				 LM, 
				 range.getType(), 
				 LM, 
				 gl.formatd(b[BUY_PROFIT],gl.AL.RIGHT,8), 
				 LM, 
				 gl.formatd(b[BUY_LOSE],gl.AL.RIGHT,8), 
				 LM, 
				 gl.formatd(b[BUY_RESULT],gl.AL.RIGHT,8), 
				 LM, 
				 gl.formatd(s[SELL_PROFIT],gl.AL.RIGHT,8), 
				 LM, 
				 gl.formatd(s[SELL_LOSE],gl.AL.RIGHT,8), 
				 LM, 
				 gl.formatd(s[SELL_RESULT],gl.AL.RIGHT,8), 
				 LM, 
				 (int)b[BUY_PROFIT_CNT], 
				 LM, 
				 (int)b[BUY_LOSE_CNT], 
				 LM, 
				 (int)b[BUY_RESULT_CNT], 
				 LM, 
				 (int)s[SELL_PROFIT_CNT], 
				 LM, 
				 (int)s[SELL_LOSE_CNT], 
				 LM, 
				 (int)s[SELL_RESULT_CNT], 
				 LM, 
				 gl.formatd(gl.getRei(b[BUY_RESULT],last_open_rate[0]),gl.AL.RIGHT,8), //  BUY_REI 
				 LM, 
				 gl.formatd(gl.getRei(s[SELL_RESULT],last_open_rate[0]),gl.AL.RIGHT,8), // SELL_REI 
				 LM, 
				 gl.formatd(gl.getRei((b[BUY_RESULT]+s[SELL_RESULT]),last_open_rate[0]),gl.AL.RIGHT,8) // RESULT_REI 
				 
				); 

					gl.smn(msg); 
					 
					if(to_file) 
					{ 
					 
					String fileExport = String.format("%s",TConfiguration.ORDERS_REPORT_FILE); 
					 
					String n_msg = String.format("%s\n",msg); 
						 
						if(!Fu.saveStringToFile(fileExport,n_msg,true,true)) 
						{ 
							gl.tracex(new Object(){},String.format("Error while write to file...%s...%s\n",fileExport,n_msg)); 
							 
							return false; 
						} 
					} 
					 
					return true; 
	} 
	 
	public static boolean doOrdersSuiteDaysRangeTickersFromFile(String source,boolean from_file,String begin_date,String end_date,List<TRange> ranges,boolean report_to_file) 
	{ 
	 
		if(Fu.deleteFile(TConfiguration.ORDERS_REPORT_FILE)) 
		{ 
			gl.tracex(new Object(){},String.format("Delete summary file...%s",gl.S_OK)); 
		} 
		else 
		{ 
			gl.tracex(new Object(){},String.format("Delete summary file...%s",gl.S_ERROR)); 
			 
			return false; 
		} 
			 
		List<String> list = Fu.getListFromFile(source); 
 
		list.forEach(a-> 
		{ 
			doOrdersSuiteDaysRange(a,from_file,begin_date,end_date,ranges,report_to_file); 
		 
		}); 
		 
		 
		return true;		 
				 
	} 
	 
	public static boolean doOrdersSuiteDaysRange(String ticker,boolean from_file,String begin_date,String end_date,List<TRange> ranges,boolean to_file) 
	{ 
		List<TPackageOne>  items = new ArrayList<TPackageOne>(); 
		 
		List<TCrossMetric> list = new ArrayList<TCrossMetric>(); 
		 
		List<TOrder> orders = new ArrayList<TOrder>(); 
		 
		if (!getCrossMetricFullPackageDaysRangeEx(ticker,from_file,begin_date,end_date,items,list)) 
			return false; 
		 
		// Write header. 
		 
		String fileExport = ""; 
		 
		if(to_file) 
		{ 
			fileExport = String.format("%s",TConfiguration.ORDERS_REPORT_FILE); 
			 
			String reportHeader = "";//String.format(TConfiguration.REPORT_HEADER,ticker.toUpperCase(),begin_date,end_date); 
			 
			if(!Fu.saveStringToFile(fileExport,reportHeader,true,true)) 
			{ 
				gl.tracex(new Object(){},String.format("Error while write to file...%s...%s\n",fileExport,reportHeader)); 
				 
				return false; 
			} 
			 
		} 
		 
		ranges.forEach(a->{ 
			 
			getOrdersPackageByCrossMetricListAndRangeEx(ticker,items,list,orders,a); 
			 
			writeOrdersPackageStat(ticker,orders,a,begin_date,end_date,to_file); 
				 
		}); 
		 
		if(to_file) 
		{ 
			fileExport = String.format("%s",TConfiguration.ORDERS_REPORT_FILE); 
			 
			String reportHeader = String.format("%s","\n"); 
			 
			if(!Fu.saveStringToFile(fileExport,reportHeader,true,true)) 
			{ 
				gl.tracex(new Object(){},String.format("Error while write to file...%s...%s\n",fileExport,reportHeader)); 
				 
				return false; 
			} 
			 
		} 
				return true; 
		 
	} 
	 
	 
	public static boolean doCrossMetricSuite(int closer,List<TPackageOne>  items,Range range) 
	{ 
		List<TCrossMetric> list = new ArrayList<TCrossMetric>(); 
		 
		if (!createCrossMetric(closer,items,list)) 
			return false; 
		 
			showCrossMetric(list,range); 
		 
			return true; 
		 
	} 
	 
	public static void showCrossMetric(List<TCrossMetric> list,Range range) 
	{ 

		int[] ma = {0,0}; 
		 
		list.forEach(c-> 
		{ 
			 
			if( c.getBegin().before(range.getDate_to()/*dt_end*/) && c.getBegin().after(range.getDate_from()/*dt_begin*/) && c.getDurability() > 1) 
			{ 
				gl.smn(c.toString()); 
				 
				ma[0]++; 
			} 
		} 
		); 
		 
		 
				gl.smn(String.format("Count of useful items : %05d",ma[0])); 
				 
	} 
	 
	public static boolean acceptFilter(List<TPackageOne> items,List<TPackageOne> filter,Range range) 
	{ 
	 
				// Filter by date. 
				 
				if(items.size() == gl.E_EMPTY) 
				{ 
					 
					gl.tx_e(new Object(){},"Empty input collection"); 
					 
					return false; 
				} 
				 
				filter.clear(); 
				 
				for(int i=0; i < items.size();i++) 
				{ 
					if(DateUtil.in_range(items.get(i).getTbi().getDate(),range.getDate_from(),range.getDate_to())) 
					{ 
						filter.add(items.get(i)); 
					} 
				} 
				 
				 
				if(filter.size() == gl.E_EMPTY) 
				{ 
					 
					gl.tx_e(new Object(){},"Empty output collection."); 
					 
					return false; 
				} 
				 
						return true; 
				 
	} 
	 
	 
	 
	public static boolean createPackageOne(String ticker,boolean from_db,List<TPackageOne> items) 
	{ 
				return getPayload(ticker,from_db,items); 
	} 
	 
	public static boolean createCrossMetric(int closer,List<TPackageOne> items,List<TCrossMetric> list) 
	{ 
		 
		 
		if(!createCrossMetricBase(items,list)) 
			return false; 
		 
		 
		 
		list.forEach(a->{ 
	 
			TCrossMetric cm = 
					list.stream().filter( 
							(b-> ( 
									b.getTarget() == closer && 
									b.getType()== ((a.getType()== TCross.UP ) ? TCross.DOWN : TCross.UP) && 
									b.getBegin().after(a.getBegin()) 
							))) 
							.findFirst() 
							.orElse(null); 
				 
			if(cm != null ) 
			{ 
				long days_between = DateUtil.days_between(a.getBegin(),cm.getBegin()); 
				 
				if (days_between > 2) 
				{ 
					a.setEnd(cm.getBegin()); 
				 
					a.setDurability(days_between); 
					 
				} 
				 
			} 
		}); 
				return true; 
	} 
	 
	 
	 
	public static boolean getCrossMetricFullPackageDaysRangeEx(String ticker,boolean from_file,String begin_date,String end_date,List<TPackageOne> items,List<TCrossMetric> list) 
	{ 
		 
		//start = System.nanoTime(); 
		 
		if(!getPayload(ticker,from_file,items)) 
			return false; 
		 
		Date begin = DateUtil.to_date(begin_date); 
		 
		Date end = DateUtil.to_date(end_date); 
		 
		List<TPackageOne> filter = new ArrayList<TPackageOne>(); 
		 
		for(int i=0; i < items.size();i++) 
		{ 
			if(DateUtil.in_range(items.get(i).getTbi().getDate(),begin,end)) 
			{ 
				filter.add(items.get(i)); 
			} 
		} 
		 
				 
		if(!createCrossMetricBase(filter,list)) 
			return false; 
		 	 
		list.forEach(a->{ 
			 
			Date cm_date = TCrossMetric.getDateByType(list,a.getTarget(),a.getType(),a.getBegin()); 
			 
			if(cm_date != null ) 
			{ 
				long days_between = DateUtil.days_between(a.getBegin(),cm_date); 
				 
				if (days_between > 1) 
				{ 
					a.setEnd(cm_date); 
				 
					a.setDurability(days_between); 
					 
				} 
				 
			} 
		}); 
				return true; 
				 
	} 
	 
	public static boolean getCrossMetricFullPackageDaysRange(String ticker,boolean from_file,String begin_date,String end_date,List<TPackageOne> items,List<TCrossMetric> list) 
	{ 
		 
		//start = System.nanoTime(); 
		 
		if(!getPayload(ticker,from_file,items)) 
			return false; 
				 
		if(!createCrossMetricBase(items,list)) 
			return false; 
		 	 
		list.forEach(a->{ 
			 
			TCrossMetric cm = 
					list.stream().filter( 
							(b-> ( 
									b.getTarget()== a.getTarget() && 
									b.getType()== ((a.getType()== TCross.UP ) ? TCross.DOWN : TCross.UP) && 
									b.getBegin().after(a.getBegin()) 
							))) 
							.findFirst() 
							.orElse(null); 
	 
			 
			if(cm != null ) 
			{ 
				long days_between = DateUtil.days_between(a.getBegin(),cm.getBegin()); 
				 
				if (days_between > 1) 
				{ 
					a.setEnd(cm.getBegin()); 
				 
					a.setDurability(days_between); 
					 
				} 
				 
			} 
		}); 
				return true; 
				 
	} 
	 
	 
	public static boolean doCrossMetricStartSuite(String ticker,boolean from_file) 
	{ 
		 
		//start = System.nanoTime(); 
		 
		List<TPackageOne> items = new ArrayList<TPackageOne>(); 
		 
		if(!getPayload(ticker,from_file,items)) 
			return true; 
		 
		List<TCrossMetric> list = new ArrayList<TCrossMetric>(); 
				 
		if(!createCrossMetricBase(items,list)) 
			return false; 
		 
		int[] dow = {0,0}; 
		 
		int[] ma = {0,0}; 
		 
		StringBuilder sb = new StringBuilder(); 
		 
		for(dow[0] = DateUtil.MONDAY;dow[0] <= DateUtil.FRIDAY;dow[0]++) 
		{ 
				sb.append(String.format("%01d",dow[0])); 
			 
			for(ma[0] = TCrossMetric.M3;ma[0] <= TCrossMetric.M200;ma[0]++) 
			{ 
				 
				long up = list.stream().filter( 
						(a-> ( 
								a.getTarget()== ma[0] && 
								a.getType()==TCross.UP && 
								DateUtil.day_of_week(a.getBegin())== dow[0] 
								 
						)) 
						).count(); 
				 
				long down= list.stream().filter( 
						(a-> ( 
								a.getTarget()== ma[0] && 
								a.getType()==TCross.DOWN && 
								DateUtil.day_of_week(a.getBegin())== dow[0] 
								 
						)) 
						).count(); 
				 
				 
						sb.append(String.format(" %04d %04d",up,down)); 
						 
			} // for ma 
			 
			// Row sum 
			 
					sb.append(String.format(" %04d %04d", 
							 
							list.stream().filter( 
									(a-> ( 
											 
											a.getType()==TCross.UP && 
											DateUtil.day_of_week(a.getBegin())== dow[0] 
											 
									)) 
									).count(), 
									list.stream().filter( 
											(a-> ( 
													 
													a.getType()==TCross.DOWN && 
													DateUtil.day_of_week(a.getBegin())== dow[0] 
													 
											)) 
											).count() 
									 
							 
							)); 
			 
					gl.smn(sb.toString()); 
					 
					sb.setLength(gl.E_EMPTY); 
					 
		} 
		 
		// Itogo last row. 
		sb.append(String.format("%s"," ")); 
		 
				 
		for(ma[0] = TCrossMetric.M3;ma[0] <= TCrossMetric.M200;ma[0]++) 
		{ 
			sb.append(String.format(" %04d %04d", 
					 
					list.stream().filter( 
							(a-> ( 
									a.getTarget()== ma[0] && 
									a.getType()==TCross.UP 
							)) 
							).count(), 
							list.stream().filter( 
									(a-> ( 
											a.getTarget()== ma[0] && 
											a.getType()==TCross.DOWN 
									)) 
									).count() 
					)); 
	 
		} // Itogo last row. 
		 
				sb.append(String.format(" %04d %04d", 
				 
				list.stream().filter( 
						(a-> ( 
								a.getType()==TCross.UP 
						)) 
						).count(), 
						list.stream().filter( 
								(a-> ( 
										a.getType()==TCross.DOWN 
								)) 
								).count() 
				)); 
		 
						gl.smn(sb.toString()); 
						 
						sb.setLength(gl.E_EMPTY); 

						 
						gl.tracex(new Object(){},String.format("Collection size...%05d", 
								list.stream().filter( 
										(a-> ( 
												a.getType()==TCross.DOWN || 
												a.getType()==TCross.UP 
										)) 
										).count() 
								)); 
		 
		/* 
		long  all 	= list.stream().mapToInt(a->a.getType()).count(); 
		 
		long  up 	= list.stream().mapToInt(a->a.getType()).filter(b->(b==TCross.UP)).count(); 
		 
		long  down 	= list.stream().mapToInt(a->a.getType()).filter(b->(b==TCross.DOWN)).count(); 
			 
		long  m3_up = list.stream().filter( 
				(a-> (a.getTarget()== TCrossMetric.M3 && a.getType()==TCross.UP)) 
				).count(); 
		 
		long m3_down = list.stream().filter( 
				(a-> (a.getTarget()== TCrossMetric.M3 && a.getType()==TCross.DOWN)) 
				).count(); 

		long m3_up_monday = list.stream().filter( 
				(a-> ( 
						a.getTarget()== TCrossMetric.M3 && 
						a.getType()==TCross.UP && 
						DateUtil.getDayOfWeek(a.getBegin())== DateUtil.MONDAY 
						 
				)) 
				).count(); 

		long m3_down_monday = list.stream().filter( 
				(a-> ( 
						a.getTarget()== TCrossMetric.M3 && 
						a.getType()==TCross.DOWN && 
						DateUtil.getDayOfWeek(a.getBegin())== DateUtil.MONDAY 
				)) 
				).count(); 
		 
		gl.smn(String.format("All items : %05d Up : %04d Down : %04d CheckSum : %05d  Re-Up: %05d Re-Down: %05d Monday-Up: %05d Monday-Down: %05d", 
				all,up,down,(up+down),m3_up,m3_down, 
				m3_up_monday,m3_down_monday 
				)); 
		*/ 
		 
				list.forEach(a->{gl.smn(a.toString());}); 
						 
				return true; 
				 
	} 
	 
	 
	public static void testTMa(String ticker,boolean from_file) 
	{ 
		start = System.nanoTime(); 
		 
		List<TPackageOne> list = new ArrayList<TPackageOne>(); 
		 
		getPayload(ticker,from_file,list); 
		 
		list.forEach(a-> 
		{ 
				if( 
						a.getTbi().getDate().before(DateUtil.to_date("01.01.1974")) 
						 
				  ) 
				{ 
					// String msg = String.format("%s %s ",a.getTbi().toString(),a.getMa().toString()); 
					 
					String msg = String.format("%s %s  %s = %s", 
							DateUtil.to_string(a.getTbi().getDate()), 
							a.getTbi().getO(), 
							a.getMa().toString(), 
							a.getCross().toString() 
							); 
							 
							gl.smn(msg); 
					 
				} 
		} 
		); 
	} 
	 
	public static boolean getPayload(String ticker,boolean from_db,List<TPackageOne> items) 
	{ 
		 
		if (!getPackageOneAsList(ticker, items, from_db)) { 
			 
			gl.tracex(new Object() {}, String.format("Payload...%s", "Error")); 

			return false; 
		} 

			gl.tracex(new Object() {}, String.format("Payload...%s...%d", "Ok",items.size())); 

			return true; 
	 
	} 
	 
	public static void doPackageOneSuite(String ticker,boolean from_file) 
	{ 
						 
		List<TPackageOne> items = new ArrayList<TPackageOne>(); 
		 
		if(!getPayload(ticker,from_file,items)) 
			return; 
		 
		List<TCrossMetric> list = getCrossMetricPackage(items); 
		 
		long  all 	= list.stream().mapToInt(a->a.getType()).count(); 
		 
		long  up 	= list.stream().mapToInt(a->a.getType()).filter(b->(b==TCross.UP)).count(); 
		 
		long  down 	= list.stream().mapToInt(a->a.getType()).filter(b->(b==TCross.DOWN)).count(); 
			 
		long  m3_up = list.stream().filter( 
				(a-> (a.getTarget()== TCrossMetric.M3 && a.getType()==TCross.UP)) 
				).count(); 
		 
		long m3_down = list.stream().filter( 
				(a-> (a.getTarget()== TCrossMetric.M3 && a.getType()==TCross.DOWN)) 
				).count(); 

		long m3_up_monday = list.stream().filter( 
				(a-> ( 
						a.getTarget()== TCrossMetric.M3 && 
						a.getType()==TCross.UP && 
						DateUtil.day_of_week(a.getBegin())== DateUtil.MONDAY 
						 
				)) 
				).count(); 

		long m3_down_monday = list.stream().filter( 
				(a-> ( 
						a.getTarget()== TCrossMetric.M3 && 
						a.getType()==TCross.DOWN && 
						DateUtil.day_of_week(a.getBegin())== DateUtil.MONDAY 
				)) 
				).count(); 
		 
		gl.smn(String.format("All items : %05d Up : %04d Down : %04d CheckSum : %05d  Re-Up: %05d Re-Down: %05d Monday-Up: %05d Monday-Down: %05d", 
				all,up,down,(up+down),m3_up,m3_down, 
				m3_up_monday,m3_down_monday 
				)); 
		 
		 
		 
	} 
	 
	public static void OnStat(String ticker,boolean from_file) 
	{ 
		TPackageOneManager pkom = TPackageOneManager.getInstance(ticker); 
		 
		if(!pkom.loadPrimary(from_file)) 
			gl.tracex(new Object(){},String.format("Operation [%s] completed with...%s...%s","PPIMARY",ticker,"Error")); 
		else 
			gl.tracex(new Object(){},String.format("Operation [%s] completed with...%s...%s","PPIMARY",ticker,"Ok")); 
		 
		if(!pkom.loadRest()) 
			gl.tracex(new Object(){},String.format("Operation [%s] completed with...%s...%s","REST",ticker,"Error")); 
		else 
			gl.tracex(new Object(){},String.format("Operation [%s] completed with...%s...%s","REST",ticker,"Ok")); 
		 
		if(!Fu.to_file(String.format("e:\\exp\\%s_pkg.txt",ticker),pkom.toString())) 
			gl.tracex(new Object(){},String.format("Operation [%s] completed with...%s...%s","SAVE",ticker,"Error")); 
		else 
			gl.tracex(new Object(){},String.format("Operation [%s] completed with...%s...%s","SAVE",ticker,"Ok")); 

		 
	} 
	public static void OnLoadTbi(String ticker,boolean from_file) 
	{ 
		TPackageOneManager pkom = TPackageOneManager.getInstance(ticker); 
		 
		if(!pkom.loadPrimary(from_file)) 
			gl.tracex(new Object(){},String.format("Operation [%s] completed with...%s","PPIMARY","Error")); 
		else 
			gl.tracex(new Object(){},String.format("Operation [%s] completed with...%s","PPIMARY","Ok")); 
		 
		if(!pkom.loadRest()) 
			gl.tracex(new Object(){},String.format("Operation [%s] completed with...%s","REST","Error")); 
		else 
			gl.tracex(new Object(){},String.format("Operation [%s] completed with...%s","REST","Ok")); 
		 
		if(!Fu.to_file(String.format("%s:\\exp\\%s.txt",TConfiguration.DISK,ticker),pkom.toStringTbi())) 
			gl.tracex(new Object(){},String.format("Operation [%s] completed with...%s","SAVE","Error")); 
		else 
			gl.tracex(new Object(){},String.format("Operation [%s] completed with...%s","SAVE","Ok")); 

		 
	} 
	 
	 
	public static void OnLoadSuite(String cmd_file,boolean from_file) 
	{ 
		String[] tickers = Fu.getStringArrayFromFile(cmd_file); 
		 
		if(tickers == null) 
		{ 
			gl.tracex(new Object(){},String.format("Input file list...%s...%s",cmd_file,"Error")); 
			 
			return; 
		} 
		 
			List<String> list = Arrays.asList(tickers); 
		 
			list.forEach(a->{OnLoad(a,from_file);}); 
		 
	} 
	 
	public static void OnLoadTbiSuite(String cmd_file,boolean from_file) 
	{ 
		 
		List<String> tickers = Fu.getListFromFile(cmd_file); 
		 
		if(tickers == null || tickers.size() == gl.E_EMPTY) 
		{ 
			gl.tracex(new Object(){},String.format("Input file list...%s...%s",cmd_file,"Error or empty.")); 
			 
			return; 
		} 
		 
			gl.tracex(new Object(){},String.format("Input file list...%s...%s...%6d",cmd_file,"Ok. rows is",tickers.size())); 
		 
			tickers.forEach(a->{OnLoadTbi(a,from_file);}); 
		 
	} 
	 
	 
	 
	 
	public static boolean task_raw(String job_file) 
	{ 
		 
		start = System.nanoTime(); 
		 
		TJob bat = new TJob(job_file); 
		 
		String msg = "Load configuration"; 
		 
		if(!bat.parse()) 
		{ 
			gl.tx_e(new Object(){},msg); 
			 
			return false; 
		} 
		 
			if (!TConfiguration.loadConfiguration(bat)) 
			{ 
				gl.tx_e(new Object(){},"Load configuration"); 

				return false; 
				 
			} 
			 
			if (!TConfiguration.TAKE_PROFIT_BOUND.isEmpty()) 
			{ 
				gl.tx_e(new Object(){},"Try to run in LOOKING best values mode"); 

				return false; 
				 
			} 
			 
			 
			List<TPortfolioStat> portfolio = new ArrayList<TPortfolioStat>(); 
			 
			List<String> tickers_list = Fu.getListFromFile(TConfiguration.LIST_FILE); 
			 
			if(tickers_list.size() == gl.E_EMPTY) 
			{ 
				gl.tx_e(new Object(){},String.format("Empty list of tickers in file... %s",TConfiguration.LIST_FILE)); 

				return false; 
			} 
			 
			List<Bar>  tbis = new ArrayList<Bar>(); 
			 
			List<Bar>  filter = new ArrayList<Bar>(); 
			 
			List<Order> orders = new ArrayList<Order>(); 
			 
			 
			StringBuilder sb = new StringBuilder(); 
			 
			StringBuilder dsb = new StringBuilder(); 
			 
			StringBuilder tsb = new StringBuilder(); 
			 
			List<Integer>  open_list = TConfiguration.open; 
			 
			List<Integer>  close_list = TConfiguration.close; 
			 
			TPl pl = new TPl(TConfiguration.TAKE_PROFIT,TConfiguration.STOP_LOSS,(TConfiguration.KEY_TOOL&0x000F),((TConfiguration.KEY_TOOL&0x00F0)>>4)); 
	 
			List<TLightOrderStat> temp_stub = new ArrayList<TLightOrderStat>(); 
			 
			int skip_days = TConfiguration.SKIP_DAYS_BEFORE_OPEN_THE_DEAL; 
			 
			tickers_list.forEach(t-> 
			{ 
				 gl.smn(""); 
				 
				if (Bar.read(t,TConfiguration.DATA_DIR,tbis)) 
				{ 
				DateUtil.year_series(DateUtil.to_date(TConfiguration.PERIOD_BEGIN),DateUtil.to_date(TConfiguration.PERIOD_END)).forEach( 
					c-> 
					{ 
				 
						Bar.accept_filter( 
								tbis, 
								filter, 
								c 
								); 
						 
						 
						open_list.forEach( o ->{ 
						 
						close_list.forEach( clo ->{ 
						 
						if 
						( 
							1==1	//MaUtil.maEngineRawV11(t,(short)((o<<12)|(clo<<8)|(TConfiguration.KEY_TOOL)),tbis, filter,orders,sb,dsb,temp_stub,pl,skip_days) 
						) 
						{ 
							 
							if(orders.size() != gl.E_EMPTY) 
							{ 
										 
										//Order.exportToPortfolio(orders,portfolio,c); 
										 
										 
										String fileAudit  = String.format("%s\\%s.%s.%s.txt",TConfiguration.REPORT_DIR,t,"audit",c.toString()); 
			 
										String fileOrders = String.format("%s\\%s.%s.%s.txt",TConfiguration.REPORT_DIR,t,"orders",c.toString()); 
										 
										String fileDetail = String.format("%s\\%s.%s.%s.txt",TConfiguration.REPORT_DIR,t,"detail",c.toString()); 
																	 
										// Create audit file. 
										 
										if(filter.size() != gl.E_EMPTY) 
										{ 
											Order.setupAudit(orders,filter,fileAudit); 
										} 
										 
										String itogo = "";//String.format("%s",Order.toStringTotal(orders)); 
										 
										// Orders file. 
										sb.append(System.lineSeparator()); 
										 
										sb.append(itogo); 
										 
										sb.append(System.lineSeparator()); 
										 
										sb.append(System.lineSeparator()); 
																	 
														Fu.saveStringToFile( 
																fileOrders, 
																sb.toString(), 
																TConfiguration.REPORT_FILE_APPEND_MODE, 
																true); 
			 
										// Save xRef if need. 
										 
										if(TConfiguration.SHOW_CROSS_XREF == gl.E_OK) 
										{ 
											String filexRef = String.format("%s\\%s.%s.%s.txt",TConfiguration.REPORT_DIR,t,"xref",c.toString()); 
											 
											Fu.saveStringToFile( 
													filexRef, 
													dsb.toString(), 
													TConfiguration.REPORT_FILE_APPEND_MODE, 
													true); 
										} 
														 
			 
												sb.setLength(gl.E_EMPTY); 
			 
												dsb.setLength(gl.E_EMPTY); 
														 
												tsb.setLength(gl.E_EMPTY); 
											 
						} // order size != 0 

						} // Engine Main cycle. 
						 
					}); // Open list. 
					 
					}); // Close list. 

					}); // date series. 
				 
							gl.smn(""); 
				 
				} // if read tbi. 
			 
			}); // Ticker list. 
						 
			 
						if(portfolio.size() != gl.E_EMPTY) 
						{ 
							String result = TPortfolioStatRow.toStringMap(portfolio); 
						 
							TPortfolioStatRow.write(result); 
							 
						} 
														 
						return true; 
	 
	} 
	 
		 
	public static boolean task_etl_week(String job_file) 
	{ 
		Object v = new Object(){}; 
		 
		start = System.nanoTime(); 
		 
		TJob bat = new TJob(job_file); 
		 
		String msg = "Load configuration"; 
		 
		if(!bat.parse()) 
		{ 
			gl.tx_e(new Object(){},msg); 
			 
			return false; 
		} 
		 
			if (!TConfiguration.loadConfiguration(bat)) 
			{ 
				gl.tx_e(new Object(){},"Load configuration"); 

				return false; 
				 
			} 
			 			 
						 
			List<String> tickers_list = Fu.getListFromFile(TConfiguration.LIST_FILE); 
			 
			gl.tx_k(v,String.format("Load tickers from file...%s",TConfiguration.LIST_FILE)); 
						 
			if(tickers_list.size() == gl.E_EMPTY) 
			{ 
				gl.tx_e(new Object(){},String.format("Empty list of tickers in file... %s",TConfiguration.LIST_FILE)); 

				return false; 
			} 
			 
			 
			 
			List<Bar>  tbis = new ArrayList<Bar>(); 
			 
			List<Bar>  tweek = new ArrayList<Bar>(); 
			 
			tickers_list.forEach(t-> 
			{ 
				 
				if (Bar.read(t,TConfiguration.DATA_DIR,tbis)) 
				{ 
				 
					// IPO. 
					Bar bi[] = {tbis.get(gl.E_EMPTY)}; 
					 
					int prev_woy[]  = {DateUtil.week_of_year(bi[0].getDate())}; 
					 
					double prev_close[]  = {bi[0].getC()}; 
					 
				    	 
					 tbis.forEach(i->{ 
				    	 
				    	 int woy = DateUtil.week_of_year(i.getDate()); 
				 
				    	 if(woy != prev_woy[0]) 
				    	 { 
				    		 // Set Close. 
				    		 
				    		 bi[0].setC(prev_close[0]); 
				    		 
				    		 
				    		 // Add new object into the list of week rate. 
				    		 
				    		 int iid = tweek.size()+1; 
				    				 
				    		 Bar add = Bar.getInstance( 
				    				 iid, 
				    				 DateUtil.to_string(i.getDate()), 
				    				 bi[0].getO(), 
				    				 bi[0].getH(), 
				    				 bi[0].getL(), 
				    				 bi[0].getC() 
				    				 ); 
				    				 
				    				 
				    		 tweek.add(add); 
				    		 
				    		 // Change prev section. 
				    		 
				    		 prev_woy[0] = woy; 
				    		 
				    		 bi[0] = i; 
				    		 
				    	 } 
				    	 else 
				    	 { 
				    		 // Jump into week. 
				    		 // Update item. 
				    		 
				    		 if(i.getH()> bi[0].getH()) 
				    			 bi[0].setH(i.getH()); 
				    		 
				    		 if(i.getL()< bi[0].getL()) 
				    			 bi[0].setL(i.getL()); 
				    		 
				    	     prev_close[0] = i.getC(); 		 
				    		 
				    	 } // if woy. 
				    	 
				    					 
				});// 
				} // read. 
				 
				String m_file_export = String.format("%s\\%s_week.txt",TConfiguration.DATA_DIR,t); 
				 
				StringBuilder sb = new StringBuilder(); 
				 
				tweek.forEach(w->{ 
					 
					 
					sb.append(w.toString()); 
					 
					sb.append(System.lineSeparator()); 
					 
				}); 
				 
				if( 
				Fu.saveStringToFile( 
						m_file_export, 
						sb.toString(), 
						TConfiguration.REPORT_FILE_APPEND_MODE, 
						true)) 
				{ 
					gl.tx_k(v,String.format("Try export to file...%s",m_file_export)); 
				} 
				else 
				{ 
					gl.tx_e(v,String.format("Try export to file...%s",m_file_export)); 
				} 
					 
			 
				 
				//gl.smn(sb.toString()); 
				 
				tbis.clear(); 
				 
				tweek.clear(); 
				 
				 
			}); // tickers list. 
			 
			return false; 
	} 
	 
	 
		public static boolean task_etl_month(String job_file) 
		{ 
			Object v = new Object(){}; 
			 
			start = System.nanoTime(); 
			 
			TJob bat = new TJob(job_file); 
			 
			String msg = "Load configuration"; 
			 
			if(!bat.parse()) 
			{ 
				gl.tx_e(new Object(){},msg); 
				 
				return false; 
			} 
			 
				if (!TConfiguration.loadConfiguration(bat)) 
				{ 
					gl.tx_e(new Object(){},"Load configuration"); 

					return false; 
					 
				} 
				 			 
							 
				List<String> tickers_list = Fu.getListFromFile(TConfiguration.LIST_FILE); 
				 
				gl.tx_k(v,String.format("Load tickers from file...%s",TConfiguration.LIST_FILE)); 
							 
				if(tickers_list.size() == gl.E_EMPTY) 
				{ 
					gl.tx_e(new Object(){},String.format("Empty list of tickers in file... %s",TConfiguration.LIST_FILE)); 

					return false; 
				} 
				 
				 
				 
				List<Bar>  tbis = new ArrayList<Bar>(); 
				 
				List<Bar>  tweek = new ArrayList<Bar>(); 
				 
				tickers_list.forEach(t-> 
				{ 
					 
					if (Bar.read(t,TConfiguration.DATA_DIR,tbis)) 
					{ 
					 
						// IPO. 
						Bar bi[] = {tbis.get(gl.E_EMPTY)}; 
						 
						// int prev_woy[]  = {DateUtil.getWeekOfYear(bi[0].getDate())}; 
						 
						int prev_woy[]  = {DateUtil.month_of_year(bi[0].getDate())}; 
						 
						double prev_close[]  = {bi[0].getC()}; 
						 
					    	 
						 tbis.forEach(i->{ 
					    	 
					    	 int woy = DateUtil.month_of_year(i.getDate()); 
					 
					    	 if(woy != prev_woy[0]) 
					    	 { 
					    		 // Set Close. 
					    		 
					    		 bi[0].setC(prev_close[0]); 
					    		 
					    		 
					    		 // Add new object into the list of week rate. 
					    		 
					    		 int iid = tweek.size()+1; 
					    				 
					    		 Bar add = Bar.getInstance( 
					    				 iid, 
					    				 DateUtil.to_string(i.getDate()), 
					    				 bi[0].getO(), 
					    				 bi[0].getH(), 
					    				 bi[0].getL(), 
					    				 bi[0].getC() 
					    				 ); 
					    				 
					    				 
					    		 tweek.add(add); 
					    		 
					    		 // Change prev section. 
					    		 
					    		 prev_woy[0] = woy; 
					    		 
					    		 bi[0] = i; 
					    		 
					    	 } 
					    	 else 
					    	 { 
					    		 // Jump into week. 
					    		 // Update item. 
					    		 
					    		 if(i.getH()> bi[0].getH()) 
					    			 bi[0].setH(i.getH()); 
					    		 
					    		 if(i.getL()< bi[0].getL()) 
					    			 bi[0].setL(i.getL()); 
					    		 
					    	     prev_close[0] = i.getC(); 		 
					    		 
					    	 } // if woy. 
					    	 
					    					 
					});// 
					} // read. 
					 
					String m_file_export = String.format("%s\\%s_month.txt",TConfiguration.DATA_DIR,t); 
					 
					StringBuilder sb = new StringBuilder(); 
					 
					tweek.forEach(w->{ 
						 
						 
						sb.append(w.toString()); 
						 
						sb.append(System.lineSeparator()); 
						 
					}); 
					 
					if( 
					Fu.saveStringToFile( 
							m_file_export, 
							sb.toString(), 
							TConfiguration.REPORT_FILE_APPEND_MODE, 
							true)) 
					{ 
						gl.tx_k(v,String.format("Try export to file...%s",m_file_export)); 
					} 
					else 
					{ 
						gl.tx_e(v,String.format("Try export to file...%s",m_file_export)); 
					} 

					//gl.smn(sb.toString()); 
					 
					tbis.clear(); 
					 
					tweek.clear(); 
					 
					 
				}); // tickers list. 
					 
					return false; 
		} 
		 
		 
		public static boolean task_etl_quartal(String job_file) 
		{ 
			Object v = new Object(){}; 
			 
			start = System.nanoTime(); 
			 
			TJob bat = new TJob(job_file); 
			 
			String msg = "Load configuration"; 
			 
			if(!bat.parse()) 
			{ 
				gl.tx_e(new Object(){},msg); 
				 
				return false; 
			} 
			 
				if (!TConfiguration.loadConfiguration(bat)) 
				{ 
					gl.tx_e(new Object(){},"Load configuration"); 

					return false; 
					 
				} 
				 			 
							 
				List<String> tickers_list = Fu.getListFromFile(TConfiguration.LIST_FILE); 
				 
				gl.tx_k(v,String.format("Load tickers from file...%s",TConfiguration.LIST_FILE)); 
							 
				if(tickers_list.size() == gl.E_EMPTY) 
				{ 
					gl.tx_e(new Object(){},String.format("Empty list of tickers in file... %s",TConfiguration.LIST_FILE)); 

					return false; 
				} 
				 
				 
				 
				List<Bar>  tbis = new ArrayList<Bar>(); 
				 
				List<Bar>  tweek = new ArrayList<Bar>(); 
				 
				tickers_list.forEach(t-> 
				{ 
					 
					if (Bar.read(t,TConfiguration.DATA_DIR,tbis)) 
					{ 
					 
						// IPO. 
						Bar bi[] = {tbis.get(gl.E_EMPTY)}; 
						 
						int prev_woy[]  = {DateUtil.quartal(bi[0].getDate())}; 
						 
						double prev_close[]  = {bi[0].getC()}; 
						 
						 tbis.forEach(i->{ 
					    	 
					    	 int woy = DateUtil.quartal(i.getDate()); 
					 
					    	 if(woy != prev_woy[0]) 
					    	 { 
					    		 // Set Close. 
					    		 
					    		 bi[0].setC(prev_close[0]); 
					    		 
					    		 
					    		 // Add new object into the list of week rate. 
					    		 
					    		 int iid = tweek.size()+1; 
					    				 
					    		 Bar add = Bar.getInstance( 
					    				 iid, 
					    				 DateUtil.to_string(i.getDate()), 
					    				 bi[0].getO(), 
					    				 bi[0].getH(), 
					    				 bi[0].getL(), 
					    				 bi[0].getC() 
					    				 ); 
					    				 
					    				 
					    		 tweek.add(add); 
					    		 
					    		 // Change prev section. 
					    		 
					    		 prev_woy[0] = woy; 
					    		 
					    		 bi[0] = i; 
					    		 
					    	 } 
					    	 else 
					    	 { 
					    		 // Jump into week. 
					    		 // Update item. 
					    		 
					    		 if(i.getH()> bi[0].getH()) 
					    			 bi[0].setH(i.getH()); 
					    		 
					    		 if(i.getL()< bi[0].getL()) 
					    			 bi[0].setL(i.getL()); 
					    		 
					    	     prev_close[0] = i.getC(); 		 
					    		 
					    	 } // if woy. 
					    	 
					    					 
					});// 
					} // read. 
					 
					String m_file_export = String.format("%s\\%s_quartal.txt",TConfiguration.DATA_DIR,t); 
					 
					StringBuilder sb = new StringBuilder(); 
					 
					tweek.forEach(w->{ 
						 
						 
						sb.append(w.toString()); 
						 
						sb.append(System.lineSeparator()); 
						 
					}); 
					 
					if( 
					Fu.saveStringToFile( 
							m_file_export, 
							sb.toString(), 
							TConfiguration.REPORT_FILE_APPEND_MODE, 
							true)) 
					{ 
						gl.tx_k(v,String.format("Try export to file...%s",m_file_export)); 
					} 
					else 
					{ 
						gl.tx_e(v,String.format("Try export to file...%s",m_file_export)); 
					} 

					//gl.smn(sb.toString()); 
					 
					tbis.clear(); 
					 
					tweek.clear(); 
					 
					 
				}); // tickers list. 
					 
					return false; 
		} 
		 
		public static boolean task_etl_year(String job_file) 
		{ 
			Object v = new Object(){}; 
			 
			start = System.nanoTime(); 
			 
			TJob bat = new TJob(job_file); 
			 
			String msg = "Load configuration"; 
			 
			if(!bat.parse()) 
			{ 
				gl.tx_e(new Object(){},msg); 
				 
				return false; 
			} 
			 
				if (!TConfiguration.loadConfiguration(bat)) 
				{ 
					gl.tx_e(new Object(){},"Load configuration"); 

					return false; 
					 
				} 
				 			 
							 
				List<String> tickers_list = Fu.getListFromFile(TConfiguration.LIST_FILE); 
				 
				gl.tx_k(v,String.format("Load tickers from file...%s",TConfiguration.LIST_FILE)); 
							 
				if(tickers_list.size() == gl.E_EMPTY) 
				{ 
					gl.tx_e(new Object(){},String.format("Empty list of tickers in file... %s",TConfiguration.LIST_FILE)); 

					return false; 
				} 
				 
				 
				 
				List<Bar>  tbis = new ArrayList<Bar>(); 
				 
				List<Bar>  tweek = new ArrayList<Bar>(); 
				 
				tickers_list.forEach(t-> 
				{ 
					 
					if (Bar.read(t,TConfiguration.DATA_DIR,tbis)) 
					{ 
					 
						// IPO. 
						Bar bi[] = {tbis.get(gl.E_EMPTY)}; 
						 
						int prev_woy[]  = {DateUtil.year(bi[0].getDate())}; 
						 
						double prev_close[]  = {bi[0].getC()}; 
						 
						 tbis.forEach(i->{ 
					    	 
					    	 int woy = DateUtil.year(i.getDate()); 
					 
					    	 if(woy != prev_woy[0]) 
					    	 { 
					    		 // Set Close. 
					    		 
					    		 bi[0].setC(prev_close[0]); 
					    		 
					    		 // Add new object into the list of week rate. 
					    		 
					    		 int iid = tweek.size()+1; 
					    				 
					    		 Bar add = Bar.getInstance( 
					    				 iid, 
					    				 DateUtil.to_string(i.getDate()), 
					    				 bi[0].getO(), 
					    				 bi[0].getH(), 
					    				 bi[0].getL(), 
					    				 bi[0].getC() 
					    				 ); 
					    				 
					    				 
					    		 tweek.add(add); 
					    		 
					    		 // Change prev section. 
					    		 
					    		 prev_woy[0] = woy; 
					    		 
					    		 bi[0] = i; 
					    		 
					    	 } 
					    	 else 
					    	 { 
					    		 // Jump into week. 
					    		 // Update item. 
					    		 
					    		 if(i.getH()> bi[0].getH()) 
					    			 bi[0].setH(i.getH()); 
					    		 
					    		 if(i.getL()< bi[0].getL()) 
					    			 bi[0].setL(i.getL()); 
					    		 
					    	     prev_close[0] = i.getC(); 		 
					    		 
					    	 } // if woy. 
					    	 
					    					 
					});// 
					} // read. 
					 
					String m_file_export = String.format("%s\\%s_year.txt",TConfiguration.DATA_DIR,t); 
					 
					StringBuilder sb = new StringBuilder(); 
					 
					tweek.forEach(w->{ 
						 
						 
						sb.append(w.toString()); 
						 
						sb.append(System.lineSeparator()); 
						 
					}); 
					 
					if( 
					Fu.saveStringToFile( 
							m_file_export, 
							sb.toString(), 
							TConfiguration.REPORT_FILE_APPEND_MODE, 
							true)) 
					{ 
						gl.tx_k(v,String.format("Try export to file...%s",m_file_export)); 
					} 
					else 
					{ 
						gl.tx_e(v,String.format("Try export to file...%s",m_file_export)); 
					} 

					//gl.smn(sb.toString()); 
					 
					tbis.clear(); 
					 
					tweek.clear(); 
					 
					 
				}); // tickers list. 
					 
					return false; 
		} 
	 
		 
			 
		public static boolean task_etl_uni_group(String job_file, int mode) 
		{ 
			 
			boolean return_state = false; 
			 
			if(((mode & Fl.ETL_YEAR) >> Fl.ETL_YEAR_MASK) == gl.E_OK) 
				return_state = task_etl_uni(job_file,0 | Fl.ETL_YEAR); 
			 
			if(((mode & Fl.ETL_QUARTAL) >> Fl.ETL_QUARTAL_MASK) == gl.E_OK) 
				return_state = task_etl_uni(job_file,0 | Fl.ETL_QUARTAL); 
			 
			if(((mode & Fl.ETL_MONTH) >> Fl.ETL_MONTH_MASK) == gl.E_OK) 
				return_state = task_etl_uni(job_file,0 | Fl.ETL_MONTH); 
			 
			if(((mode & Fl.ETL_WEEK) >> Fl.ETL_WEEK_MASK) == gl.E_OK) 
				return_state = task_etl_uni(job_file,0|Fl.ETL_WEEK); 
			 
			return return_state; 
		 
		} 
		 
	public static boolean task_etl_uni(String job_file, int mode) 
		{ 
			Object v = new Object(){}; 
			 
			start = System.nanoTime(); 
			 
			TJob bat = new TJob(job_file); 
			 
			String msg = "Load configuration"; 
			 
			if(!bat.parse()) 
			{ 
				gl.tx_e(new Object(){},msg); 
				 
				return false; 
			} 
			 
				if (!TConfiguration.loadConfiguration(bat)) 
				{ 
					gl.tx_e(new Object(){},"Load configuration"); 

					return false; 
					 
				} 
				 			 
							 
				List<String> tickers_list = Fu.getListFromFile(TConfiguration.LIST_FILE); 
				 
				gl.tx_k(v,String.format("Load tickers from file...%s",TConfiguration.LIST_FILE)); 
							 
				if(tickers_list.size() == gl.E_EMPTY) 
				{ 
					gl.tx_e(new Object(){},String.format("Empty list of tickers in file... %s",TConfiguration.LIST_FILE)); 

					return false; 
				} 
				 
				List<Bar>  tbis = new ArrayList<Bar>(); 
				 
				List<Bar>  tweek = new ArrayList<Bar>(); 
				 
				String[] file_body_suffix = {""}; 
				 
				if(((mode & Fl.ETL_YEAR) >> Fl.ETL_YEAR_MASK) == gl.E_OK) 
					file_body_suffix[0] = "year"; 
				else if(((mode & Fl.ETL_QUARTAL) >> Fl.ETL_QUARTAL_MASK) == gl.E_OK) 
					file_body_suffix[0] = "quartal"; 
				else if(((mode & Fl.ETL_MONTH) >> Fl.ETL_MONTH_MASK) == gl.E_OK) 
					file_body_suffix[0] = "month"; 
				else if(((mode & Fl.ETL_WEEK) >> Fl.ETL_WEEK_MASK) == gl.E_OK) 
					file_body_suffix[0] = "week"; 
				 
				tickers_list.forEach(t-> 
				{ 
					 
					if (Bar.read(t,TConfiguration.DATA_DIR,tbis)) 
					{ 
					 
						// IPO. 
						Bar bi[] = {tbis.get(gl.E_EMPTY)}; 
						 
						int prev_woy[]  = {0}; 
						 
						if(((mode & Fl.ETL_YEAR) >> Fl.ETL_YEAR_MASK) == gl.E_OK) 
							prev_woy[0] = DateUtil.year(bi[0].getDate()); 
						else if(((mode & Fl.ETL_QUARTAL) >> Fl.ETL_QUARTAL_MASK) == gl.E_OK) 
								prev_woy[0] = DateUtil.quartal(bi[0].getDate()); 
						else if(((mode & Fl.ETL_MONTH) >> Fl.ETL_MONTH_MASK) == gl.E_OK) 
							prev_woy[0] = DateUtil.month_of_year(bi[0].getDate()); 
						else if(((mode & Fl.ETL_WEEK) >> Fl.ETL_WEEK_MASK) == gl.E_OK) 
							prev_woy[0] = DateUtil.week_of_month(bi[0].getDate()); 
						 
						double prev_close[]  = {bi[0].getC()}; 
						 
						 tbis.forEach(i->{ 
					    	 
					    	 int woy = DateUtil.year(i.getDate()); 
					    	 
					    	 if(((mode & Fl.ETL_YEAR) >> Fl.ETL_YEAR_MASK) == gl.E_OK) 
									woy = DateUtil.year(i.getDate()); 
								else if(((mode & Fl.ETL_QUARTAL) >> Fl.ETL_QUARTAL_MASK) == gl.E_OK) 
									woy  = DateUtil.quartal(i.getDate()); 
								else if(((mode & Fl.ETL_MONTH) >> Fl.ETL_MONTH_MASK) == gl.E_OK) 
									woy = DateUtil.month_of_year(i.getDate()); 
								else if(((mode & Fl.ETL_WEEK) >> Fl.ETL_WEEK_MASK) == gl.E_OK) 
									woy = DateUtil.week_of_month(i.getDate()); 
							 
					 
					    	 if(woy != prev_woy[0]) 
					    	 { 
					    		 // Set Close. 
					    		 
					    		 bi[0].setC(prev_close[0]); 
					    		 
					    		 // Add new object into the list of week rate. 
					    		 
					    		 int iid = tweek.size()+1; 
					    				 
					    		 Bar add = Bar.getInstance( 
					    				 iid, 
					    				 DateUtil.to_string(i.getDate()), 
					    				 bi[0].getO(), 
					    				 bi[0].getH(), 
					    				 bi[0].getL(), 
					    				 bi[0].getC() 
					    				 ); 
					    				 
					    				 
					    		 tweek.add(add); 
					    		 
					    		 // Change prev section. 
					    		 
					    		 prev_woy[0] = woy; 
					    		 
					    		 bi[0] = i; 
					    		 
					    	 } 
					    	 else 
					    	 { 
					    		 // Jump into week. 
					    		 // Update item. 
					    		 
					    		 if(i.getH()> bi[0].getH()) 
					    			 bi[0].setH(i.getH()); 
					    		 
					    		 if(i.getL()< bi[0].getL()) 
					    			 bi[0].setL(i.getL()); 
					    		 
					    	     prev_close[0] = i.getC(); 		 
					    		 
					    	 } // if woy. 
					    	 
					    					 
					});// 
					} // read. 
					 
					String m_file_export = String.format("%s\\%s_%s.txt",TConfiguration.DATA_DIR,t,file_body_suffix[0]); 
					 
					StringBuilder sb = new StringBuilder(); 
					 
					tweek.forEach(w->{ 
						 
						 
						sb.append(w.toString()); 
						 
						sb.append(System.lineSeparator()); 
						 
					}); 
					 
					if( 
					Fu.saveStringToFile( 
							m_file_export, 
							sb.toString(), 
							TConfiguration.REPORT_FILE_APPEND_MODE, 
							true)) 
					{ 
						gl.tx_k(v,String.format("Try export to file...%s",m_file_export)); 
					} 
					else 
					{ 
						gl.tx_e(v,String.format("Try export to file...%s",m_file_export)); 
					} 

					//gl.smn(sb.toString()); 
					 
					tbis.clear(); 
					 
					tweek.clear(); 
					 
					 
				}); // tickers list. 
					 
					return false; 
		} 
		 
		 
	 
	public static boolean task_look(String job_file) 
	{ 
		 
		start = System.nanoTime(); 
		 
		TJob bat = new TJob(job_file); 
		 
		String msg = "Load configuration"; 
		 
		if(!bat.parse()) 
		{ 
			gl.tx_e(new Object(){},msg); 
			 
			return false; 
		} 
		 
			if (!TConfiguration.loadConfiguration(bat)) 
			{ 
				gl.tx_e(new Object(){},"Load configuration"); 

				return false; 
				 
			} 
			 
			 
			if (TConfiguration.TAKE_PROFIT_BOUND.isEmpty() || TConfiguration.STOP_LOSS_BOUND.isEmpty()) 
			{ 
				gl.tx_e(new Object(){},"P(ease set up LOOKING  TP&SL range mode"); 

				return false; 
				 
			} 
			 
			 
			List<TPortfolioStat> po = new ArrayList<TPortfolioStat>(); 
			 
			List<String> tickers_list = Fu.getListFromFile(TConfiguration.LIST_FILE); 
			 
			if(tickers_list.size() == gl.E_EMPTY) 
			{ 
				gl.tx_e(new Object(){},String.format("Empty list of tickers in file... %s",TConfiguration.LIST_FILE)); 

				return false; 
			} 
			 
			List<Bar>  tbis = new ArrayList<Bar>(); 
			 
			List<Bar>  filter = new ArrayList<Bar>(); 
			 
			List<Order> orders = new ArrayList<Order>(); 
			 
			 
			StringBuilder sb = new StringBuilder(); 
			 
			StringBuilder dsb = new StringBuilder(); 
			 
			List<TLightOrderStat> tsb = new ArrayList<TLightOrderStat>(); 
			 
			List<TLightOrderStat> top_rating = new ArrayList<TLightOrderStat>(); 
						 
			 
			List<Integer>  open_list = TConfiguration.open; 
			 
			List<Integer>  close_list = TConfiguration.close; 
			 
			TPl pl = new TPl( 
					TConfiguration.TAKE_PROFIT, 
					TConfiguration.STOP_LOSS, 
					(TConfiguration.KEY_TOOL&0x000F), 
					((TConfiguration.KEY_TOOL&0x00F0)>>4) 
					); 
			 
			List<TLightOrderStat> temp_stub = new ArrayList<TLightOrderStat>(); 
			 
			 
			// Make SL series. 
			 
			List<Double> sl_series = Order.getDoubleSeries(TConfiguration.STOP_LOSS_BOUND,TConfiguration.RANGE_STEP); 
			 
			List<Double> tp_series = Order.getDoubleSeries(TConfiguration.TAKE_PROFIT_BOUND,TConfiguration.RANGE_STEP); 
			 
			 
			tickers_list.forEach(t-> 
			{ 
				 
				if (Bar.read(t,TConfiguration.DATA_DIR,tbis)) 
				{ 
					 
					String [] fileOrders = {""}; 

				DateUtil.year_series(DateUtil.to_date(TConfiguration.PERIOD_BEGIN),DateUtil.to_date(TConfiguration.PERIOD_END)).forEach( 
					c-> 
					{ 
				 
						Bar.accept_filter( 
								tbis, 
								filter, 
								c 
								); 
						 
						 
						open_list.forEach( o ->{ 
						 
						close_list.forEach( clo ->{ 
							 
							 
						sl_series.forEach(stl -> { 
						 
						tp_series.forEach(tkp -> { 
						 
							 
						 
						 
						// Set up SL&TP. 
							 
							pl.setStop_loss(stl); 
							 
							pl.setTake_profit(tkp); 
							 
						if 
						( 1==1
								//MaUtil.maEngineRawV7(t,o,clo,tbis, filter,orders,sb,dsb,tsb,pl) 
								 
								//MaUtil.maEngineRawV9(t,(short)((o<<12)|(clo<<8)|(TConfiguration.KEY_TOOL)),tbis, filter,orders,sb,dsb,temp_stub,pl) 
						) 
						{ 
							 
							if(orders.size() != gl.E_EMPTY) 
							{ 
							 
							//Order.exportToPortfolio(orders,po,c); 
							 
							fileOrders[0] = String.format("%s\\%s.%s.%s.txt",TConfiguration.REPORT_DIR,t,"orders",c.toString()); 
							 
							//String fileDetail = String.format("%s\\%s.%s.%s.txt",TConfiguration.REPORT_DIR,t,"detail",c.toString()); 
								 
							// Orders file. 
							sb.append(System.lineSeparator()); 
							 
							sb.append(System.lineSeparator()); 
														 
											Fu.saveStringToFile( 
													fileOrders[0], 
													sb.toString(), 
													TConfiguration.REPORT_FILE_APPEND_MODE, 
													true); 
											 
											 
											/* 
											FileUtil.saveStringToFile( 
													fileDetail, 
													dsb.toString(), 
													TConfiguration.REPORT_FILE_APPEND_MODE, 
													true); 
											*/ 

											if(TConfiguration.REPORT_DETAIL_MODE) 
											{ 
												gl.smn(sb.toString()); 
											} 
											 
											sb.setLength(gl.E_EMPTY); 

											dsb.setLength(gl.E_EMPTY); 
											 
											 
											 
						} // order size != 0 
						else 
						{ 
							// Make xref. 
							 
							/* 
							String filexRef = String.format("%s\\%s.%s.%s.txt",TConfiguration.REPORT_DIR,t,"xref",c.toString()); 

							String fileQuotes = String.format("%s\\%s.%s.%s.txt",TConfiguration.REPORT_DIR,t,"quotes",c.toString()); 
							 
							FileUtil.saveStringToFile( 
									filexRef, 
									dsb.toString(), 
									TConfiguration.REPORT_FILE_APPEND_MODE, 
									true); 
							 
							StringBuilder sbq = new StringBuilder(); 
							 
							filter.forEach(a-> 
							{ 
									sbq.append(a.toString()); 
									 
									sbq.append(System.lineSeparator()); 
							} 
							); 
							 
							FileUtil.saveStringToFile( 
									fileQuotes, 
									sbq.toString(), 
									TConfiguration.REPORT_FILE_APPEND_MODE, 
									true); 
							 

							 */ 
						} // xRef 

						} // One Cycle Engine. 
						 
						}); // TP list. 
						 
						}); // SL list. 
						 
					}); // Open list. 
					 
					}); // Close list. 

						// Convert to text. 
						// Create sorted list. 
						 
						List<TLightOrderStat> tsb_sorted = TSorter.getSortedByRslt(tsb,false); 
						 
						StringBuilder sb_stat = new StringBuilder(); 
						 
						 
						int index[] = {gl.E_EMPTY}; 
						 
						int TOP_LIST = 10; 
						 
						 
						tsb_sorted.forEach(sta->{ 
							 
							sb_stat.append(sta); 
							 
							sb_stat.append(System.lineSeparator()); 
							 
							if(index[0] < TOP_LIST) 
							{ 
								top_rating.add(sta); 
							} 
							 
							index[0]++; 
							 
						}); 
						 
						Fu.saveStringToFile( 
								fileOrders[0], 
								sb_stat.toString(), 
								TConfiguration.REPORT_FILE_APPEND_MODE, 
								true); 
						 
						sb_stat.setLength(gl.E_EMPTY); 
						 
						tsb.clear(); 
						 
					}); // date series. 
				 
				} // if read tbi. 
			 
			}); // Ticker list. 
						 
						if(po.size() != gl.E_EMPTY) 
						{ 
							String result = TPortfolioStatRow.toStringMap(po,pl); 
						 
							TPortfolioStatRow.write(result); 
							 
						} 
							 
						 
						 
						if(top_rating.size() != gl.E_EMPTY) 
						{ 
							TPortfolioStatRow.writeRatings(top_rating); 
						} 
						 
						 
						 
						return true; 
	 
	} 
	 
	public static void task_cross(String job_file) 
	{ 
		 
		TJob bat = new TJob(job_file); 
		 
		String msg = "Load configuration...%s"; 
		 
		if(!bat.parse()) 
		{ 
			gl.tracex(new Object(){},String.format(msg,gl.S_ERROR)); 
			 
			return; 
		} 
		 
			gl.tracex(new Object(){},String.format(msg,gl.S_OK)); 
			 
			TConfiguration.loadConfiguration(bat); 
			 
			 
			List<String> tickers_list = Fu.getListFromFile(TConfiguration.LIST_FILE); 
			 
			List<TPackageOne>  pkos = new ArrayList<TPackageOne>(); 
			 
			List<TPackageOne>  filter = new ArrayList<TPackageOne>(); 
			 
			Range range = new Range(TConfiguration.PERIOD_BEGIN,TConfiguration.PERIOD_END); 
			 
			 
			createPackageOne(tickers_list.get(gl.E_EMPTY),TConfiguration.DATA_STREAM_FROM_DB,pkos); 
			 
			acceptFilter( 
					pkos, 
					filter, 
					range 
					); 
			 
			//doCrossMetricSuite(StringUtil.getListOfIntegers(bat.getClose_list()).get(gl.E_EMPTY),filter,range); 
			 
			filter.forEach(a->{ 
				 
				//gl.smn(a.getCross().toStringNess()); 
				 
				a.getMa().setDt(a.getCross().getDt()); 

				gl.smn(String.format("%s %s",gl.fmt2(a.getTbi().getO()),a.getMa().toString())); 
				 
			}); 
	} 

	public static void main(String[] args) { 
		 
		 
		if(args.length != 2) 
		{ 
			 
			gl.tracex(new Object(){},String.format("Command line is empty...Error.")); 
					 
			return; 
			 
		} 
		 
			gl.tracex(new Object(){},String.format("Command line is ...%s...%s",args[0],args[1])); 
		 
			 
			Double passwd = Double.parseDouble(args[1].trim()); 
			 
			 
			if (passwd != 4321.1234) 
			{ 
				gl.tracex(new Object(){},String.format("Activation param...Error.")); 
				 
				return; 
			} 
			 
		 
			String startup = args[0].trim(); 
		 
			 
			if(startup.indexOf("_look") != gl.E_ERROR) 
			{ 
				gl.tracex(new Object(){},String.format("Startup in LOOK UP mode.")); 
				 
				task_look(startup); 
				 
			} 
			else 
			{ 
				gl.tracex(new Object(){},String.format("Startup in RAW mode.")); 
				 
				//task_cross(startup); 
				 
				task_raw(startup); 
				 
				// task_etl_week(startup); 
				 
				// task_etl_month(startup); 
				 
				// task_etl_quartal(startup); 
				 
				//task_etl_uni_group(startup,0|Fl.ETL_MONTH|Fl.ETL_QUARTAL|Fl.ETL_WEEK|Fl.ETL_YEAR); 
				 
			} 
		 
		gl.smn(""); 
		 
		gl.tracex(new Object(){},String.format("Suite completed in...%d ms.",(System.nanoTime()-start)/1000000)); 

	} 

	public static long start = 0L; 
	 
	 
} 

/* 

disk=e 

data_dir=%s:\exp\data 

report_dir=%s:\exp\stat\report 

report_file=a.txt 

list_dir=%s:\exp\stat\list 

list_file=week.convert 

data_stream_from_db=0 

version=v1 

*/ 
// Revision : 10.09.2018 12:49:14 
