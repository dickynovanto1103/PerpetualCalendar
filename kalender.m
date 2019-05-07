clc, clear all;

tgl = input('Masukkan tanggal (DDMMYYYY): ');
y1 = mod(tgl,10000000);
y2 = mod(y1,1000000);
y3 = mod(y2,100000);
y4 = mod(y3,10000);

tgl1 = (tgl-y4)/10000 ;

bulan1 = mod(tgl1,1000);
bulan2 = mod(bulan1,100);

if bulan2 == 1
    m = 11;
    y4 = y4-1 ;
else if bulan2 == 2
        m = 12;
        y4 = y4-1 ;
else if bulan2 == 3
        m = 1;     
else if bulan2 == 4
        m = 2;
else if bulan2 == 5
        m = 3;
else if bulan2 == 6
        m = 4;
else if bulan2 == 7
        m = 5;
else if bulan2 == 8
        m = 6;
else if bulan2 == 9
        m = 7;
else if bulan2 == 10
        m = 8;
else if bulan2 == 11
        m = 9;
else if bulan2 == 12
        m = 10;
    end
    end
    end
    end
    end
    end
    end
    end
    end
    end
    end
end

y5 = mod(y4,1000);
Y = mod(y5,100);
C = (y4-Y)/100 ;

k = (tgl1-bulan2)/100;

W = mod((k + floor((2.6*m)-0.2) - (2*C) + Y + floor(Y/4) + floor(C/4)), 7);

if W == 0 
    disp('hari : minggu')
else if W == 1
        disp('hari : senin')
else if W == 2
        disp('hari = selasa')
else if W == 3
        disp('hari = rabu')
else if W == 4
        disp('hari = kamis')
else if W == 5
        disp('hari = jumat')
else if W == 6
        disp('hari = sabtu')
    end
    end
    end
    end
    end
    end
end

    
        
    
