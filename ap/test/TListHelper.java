 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
package ap.test; 

import java.util.*; 


public class TListHelper { 

 
	 
	 
	 
	/* 
	 -- alter table t_stat_raw add constraint pk_ticker_type  primary key (TICKER,TYPE); 

create table t_stat_raw 
( 
ticker varchar2(10) not null, 
tp number(2) not null, 
bp number(6,2), 
bl number(6,2), 
br number(6,2), 
 
sp number(6,2), 
sl number(6,2), 
sr number(6,2), 
 
bpc number(6), 
blc number(6), 
brc number(6), 
 
spc number(6), 
slc number(6), 
src number(6), 
 
brei number(6,2), 
srei number(6,2), 
rrei number(6,2) 
 
); 
); 

LOAD DATA 
INFILE 'e:\bin\expimp\tera\sqlldr\in\fibo\txt\fibo.txt' 
INTO TABLE t_stat_raw 
APPEND 
FIELDS TERMINATED BY ',' 
TRAILING NULLCOLS 
(ticker, 
tp, 
BP "TO_NUMBER(:\"BP\", '999999999D99', 'NLS_NUMERIC_CHARACTERS=''.,''')", 
BL "TO_NUMBER(:\"BL\", '999999999D99', 'NLS_NUMERIC_CHARACTERS=''.,''')", 
BR "TO_NUMBER(:\"BR\", '999999999D99', 'NLS_NUMERIC_CHARACTERS=''.,''')", 
SP "TO_NUMBER(:\"SP\", '999999999D99', 'NLS_NUMERIC_CHARACTERS=''.,''')", 
SL "TO_NUMBER(:\"SL\", '999999999D99', 'NLS_NUMERIC_CHARACTERS=''.,''')", 
SR "TO_NUMBER(:\"SR\", '999999999D99', 'NLS_NUMERIC_CHARACTERS=''.,''')", 
bpc, 
blc, 
brc, 
spc, 
slc, 
src, 
BREI "TO_NUMBER(:\"SP\", '999999999D99', 'NLS_NUMERIC_CHARACTERS=''.,''')", 
SREI "TO_NUMBER(:\"SL\", '999999999D99', 'NLS_NUMERIC_CHARACTERS=''.,''')", 
RREI "TO_NUMBER(:\"SR\", '999999999D99', 'NLS_NUMERIC_CHARACTERS=''.,''')" 

) 
*/ 
	 
/* 
disk=e 

svn_source_dir= 
 
svn_time_control=18.01.2018_09:00:00 
 
svn_time_direction=> 
 
svn_update_file= 

*/ 
	 
/* 
disk=e 

data_dir=e:\\exp\\data 

report_dir=e:\\exp\stat\\report 

report_file=a.report.txt 

list_dir=e:\exp\stat\list 

list_file=a.orders.short.list 

start_date=01.01.2010 

end_date=31.03.2010 

output_forms=trace,file 

report_column_delimiter=, 

report_file_append_mode=off 

*/ 
	 
} 
// Revision : 10.09.2018 12:49:16 
