package com.document;


import org.apache.commons.lang.StringUtils;

/**
 * Created by za-wanglinlin on 2019/1/28.
 */
public enum OccupationEnum {
    OccupationEnum1("0101", "6"),
    OccupationEnum2("0102", "6"),
    OccupationEnum3("0103", "6"),
    OccupationEnum4("0104", "6"),
    OccupationEnum5("0105", "6"),
    OccupationEnum6("0106", "6"),
    OccupationEnum7("0107", "6"),
    OccupationEnum8("0108", "6"),
    OccupationEnum9("0109", "6"),
    OccupationEnum10("0110", "6"),
    OccupationEnum11("0111", "6"),
    OccupationEnum12("0112", "6"),
    OccupationEnum13("0113", "6"),
    OccupationEnum14("0114", "6"),
    OccupationEnum15("0115", "6"),
    OccupationEnum16("0116", "6"),
    OccupationEnum17("0117", "6"),
    OccupationEnum18("0118", "6"),
    OccupationEnum19("0119", "6"),
    OccupationEnum20("0120", "6"),
    OccupationEnum21("0121", "6"),
    OccupationEnum22("0122", "6"),
    OccupationEnum23("0123", "6"),
    OccupationEnum24("0124", "6"),
    OccupationEnum25("0125", "6"),
    OccupationEnum26("0126", "6"),
    OccupationEnum27("0127", "6"),
    OccupationEnum28("0128", "6"),
    OccupationEnum29("0129", "6"),
    OccupationEnum30("0130", "6"),
    OccupationEnum31("0131", "6"),
    OccupationEnum32("0132", "6"),
    OccupationEnum33("0133", "6"),
    OccupationEnum34("0134", "6"),
    OccupationEnum35("0199", "6"),
    OccupationEnum36("0201", "2"),
    OccupationEnum37("0202", "2"),
    OccupationEnum38("0203", "2"),
    OccupationEnum39("0204", "2"),
    OccupationEnum40("0205", "2"),
    OccupationEnum41("0206", "2"),
    OccupationEnum42("0207", "2"),
    OccupationEnum43("0208", "2"),
    OccupationEnum44("0209", "2"),
    OccupationEnum45("0210", "2"),
    OccupationEnum46("0211", "2"),
    OccupationEnum47("0212", "2"),
    OccupationEnum48("0213", "2"),
    OccupationEnum49("0214", "2"),
    OccupationEnum50("0215", "2"),
    OccupationEnum51("0216", "2"),
    OccupationEnum52("0217", "2"),
    OccupationEnum53("0218", "2"),
    OccupationEnum54("0219", "2"),
    OccupationEnum55("0220", "2"),
    OccupationEnum56("0221", "2"),
    OccupationEnum57("0222", "2"),
    OccupationEnum58("0223", "2"),
    OccupationEnum59("0224", "2"),
    OccupationEnum60("0225", "2"),
    OccupationEnum61("0226", "2"),
    OccupationEnum62("0227", "2"),
    OccupationEnum63("0228", "2"),
    OccupationEnum64("0229", "2"),
    OccupationEnum65("0230", "2"),
    OccupationEnum66("0231", "2"),
    OccupationEnum67("0232", "2"),
    OccupationEnum68("0233", "2"),
    OccupationEnum69("0234", "2"),
    OccupationEnum70("0235", "2"),
    OccupationEnum71("0236", "2"),
    OccupationEnum72("0237", "2"),
    OccupationEnum73("0238", "2"),
    OccupationEnum74("0239", "2"),
    OccupationEnum75("0240", "2"),
    OccupationEnum76("0241", "2"),
    OccupationEnum77("0242", "2"),
    OccupationEnum78("0243", "2"),
    OccupationEnum79("0244", "2"),
    OccupationEnum80("0245", "2"),
    OccupationEnum81("0246", "2"),
    OccupationEnum82("0247", "2"),
    OccupationEnum83("0248", "2"),
    OccupationEnum84("0249", "2"),
    OccupationEnum85("0250", "2"),
    OccupationEnum86("0251", "2"),
    OccupationEnum87("0252", "2"),
    OccupationEnum88("0253", "2"),
    OccupationEnum89("0254", "2"),
    OccupationEnum90("0255", "2"),
    OccupationEnum91("0256", "2"),
    OccupationEnum92("0257", "2"),
    OccupationEnum93("0258", "2"),
    OccupationEnum94("0259", "2"),
    OccupationEnum95("0260", "2"),
    OccupationEnum96("0261", "2"),
    OccupationEnum97("0262", "2"),
    OccupationEnum98("0299", "2"),
    OccupationEnum99("0301", "2"),
    OccupationEnum100("0302", "2"),
    OccupationEnum101("0303", "2"),
    OccupationEnum102("0304", "2"),
    OccupationEnum103("0305", "2"),
    OccupationEnum104("0306", "2"),
    OccupationEnum105("0307", "2"),
    OccupationEnum106("0308", "2"),
    OccupationEnum107("0309", "2"),
    OccupationEnum108("0310", "2"),
    OccupationEnum109("0311", "2"),
    OccupationEnum110("0312", "2"),
    OccupationEnum111("0313", "2"),
    OccupationEnum112("0314", "2"),
    OccupationEnum113("0315", "2"),
    OccupationEnum114("0316", "2"),
    OccupationEnum115("0317", "2"),
    OccupationEnum116("0318", "2"),
    OccupationEnum117("0319", "2"),
    OccupationEnum118("0320", "2"),
    OccupationEnum119("0321", "2"),
    OccupationEnum120("0322", "2"),
    OccupationEnum121("0323", "2"),
    OccupationEnum122("0324", "2"),
    OccupationEnum123("0325", "2"),
    OccupationEnum124("0326", "2"),
    OccupationEnum125("0327", "2"),
    OccupationEnum126("0328", "2"),
    OccupationEnum127("0399", "2"),
    OccupationEnum128("0401", "2"),
    OccupationEnum129("0402", "2"),
    OccupationEnum130("0403", "2"),
    OccupationEnum131("0404", "2"),
    OccupationEnum132("0405", "2"),
    OccupationEnum133("0406", "2"),
    OccupationEnum134("0407", "2"),
    OccupationEnum135("0408", "2"),
    OccupationEnum136("0409", "2"),
    OccupationEnum137("0410", "2"),
    OccupationEnum138("0411", "2"),
    OccupationEnum139("0412", "2"),
    OccupationEnum140("0413", "2"),
    OccupationEnum141("0414", "2"),
    OccupationEnum142("0415", "2"),
    OccupationEnum143("0416", "2"),
    OccupationEnum144("0417", "2"),
    OccupationEnum145("0499", "2"),
    OccupationEnum146("0501", "1"),
    OccupationEnum147("0502", "1"),
    OccupationEnum148("0503", "1"),
    OccupationEnum149("0504", "1"),
    OccupationEnum150("0505", "1"),
    OccupationEnum151("0506", "1"),
    OccupationEnum152("0507", "1"),
    OccupationEnum153("0508", "1"),
    OccupationEnum154("0509", "1"),
    OccupationEnum155("0510", "1"),
    OccupationEnum156("0511", "1"),
    OccupationEnum157("0512", "1"),
    OccupationEnum158("0513", "1"),
    OccupationEnum159("0514", "1"),
    OccupationEnum160("0515", "1"),
    OccupationEnum161("0516", "1"),
    OccupationEnum162("0517", "1"),
    OccupationEnum163("0518", "1"),
    OccupationEnum164("0519", "1"),
    OccupationEnum165("0520", "1"),
    OccupationEnum166("0521", "1"),
    OccupationEnum167("0522", "1"),
    OccupationEnum168("0523", "1"),
    OccupationEnum169("0524", "1"),
    OccupationEnum170("0525", "1"),
    OccupationEnum171("0526", "1"),
    OccupationEnum172("0527", "1"),
    OccupationEnum173("0528", "1"),
    OccupationEnum174("0529", "1"),
    OccupationEnum175("0530", "1"),
    OccupationEnum176("0531", "1"),
    OccupationEnum177("0532", "1"),
    OccupationEnum178("0533", "1"),
    OccupationEnum179("0534", "1"),
    OccupationEnum180("0599", "1"),
    OccupationEnum181("0601", "3"),
    OccupationEnum182("0602", "3"),
    OccupationEnum183("0603", "3"),
    OccupationEnum184("0604", "6"),
    OccupationEnum185("0605", "6"),
    OccupationEnum186("0606", "6"),
    OccupationEnum187("0607", "6"),
    OccupationEnum188("0608", "6"),
    OccupationEnum189("0609", "3"),
    OccupationEnum190("0610", "3"),
    OccupationEnum191("0611", "3"),
    OccupationEnum192("0612", "3"),
    OccupationEnum193("0613", "6"),
    OccupationEnum194("0614", "6"),
    OccupationEnum195("0615", "6"),
    OccupationEnum196("0616", "3"),
    OccupationEnum197("0617", "3"),
    OccupationEnum198("0618", "6"),
    OccupationEnum199("0619", "6"),
    OccupationEnum200("0620", "3"),
    OccupationEnum201("0621", "6"),
    OccupationEnum202("0622", "6"),
    OccupationEnum203("0623", "3"),
    OccupationEnum204("0624", "3"),
    OccupationEnum205("0625", "6"),
    OccupationEnum206("0626", "3"),
    OccupationEnum207("0627", "3"),
    OccupationEnum208("0628", "3"),
    OccupationEnum209("0629", "3"),
    OccupationEnum210("0630", "6"),
    OccupationEnum211("0631", "3"),
    OccupationEnum212("0632", "3"),
    OccupationEnum213("0633", "3"),
    OccupationEnum214("0634", "6"),
    OccupationEnum215("0635", "6"),
    OccupationEnum216("0636", "6"),
    OccupationEnum217("0637", "6"),
    OccupationEnum218("0638", "6"),
    OccupationEnum219("0639", "3"),
    OccupationEnum220("0640", "6"),
    OccupationEnum221("0641", "6"),
    OccupationEnum222("0642", "6"),
    OccupationEnum223("0643", "3"),
    OccupationEnum224("0644", "3"),
    OccupationEnum225("0645", "3"),
    OccupationEnum226("0646", "3"),
    OccupationEnum227("0647", "3"),
    OccupationEnum228("0648", "3"),
    OccupationEnum229("0649", "3"),
    OccupationEnum230("0650", "3"),
    OccupationEnum231("0651", "6"),
    OccupationEnum232("0652", "6"),
    OccupationEnum233("0653", "6"),
    OccupationEnum234("0654", "6"),
    OccupationEnum235("0655", "6"),
    OccupationEnum236("0656", "6"),
    OccupationEnum237("0657", "6"),
    OccupationEnum238("0658", "6"),
    OccupationEnum239("0659", "6"),
    OccupationEnum240("0660", "6"),
    OccupationEnum241("0661", "3"),
    OccupationEnum242("0662", "6"),
    OccupationEnum243("0663", "6"),
    OccupationEnum244("0664", "6"),
    OccupationEnum245("0665", "6"),
    OccupationEnum246("0666", "6"),
    OccupationEnum247("0667", "6"),
    OccupationEnum248("0668", "6"),
    OccupationEnum249("0669", "6"),
    OccupationEnum250("0670", "6"),
    OccupationEnum251("0671", "6"),
    OccupationEnum252("0672", "6"),
    OccupationEnum253("0673", "6"),
    OccupationEnum254("0674", "6"),
    OccupationEnum255("0675", "6"),
    OccupationEnum256("0676", "6"),
    OccupationEnum257("0677", "6"),
    OccupationEnum258("0678", "6"),
    OccupationEnum259("0679", "6"),
    OccupationEnum260("0680", "6"),
    OccupationEnum261("0681", "6"),
    OccupationEnum262("0682", "6"),
    OccupationEnum263("0683", "6"),
    OccupationEnum264("0684", "6"),
    OccupationEnum265("0685", "6"),
    OccupationEnum266("0686", "6"),
    OccupationEnum267("0687", "6"),
    OccupationEnum268("0688", "6"),
    OccupationEnum269("0689", "6"),
    OccupationEnum270("0690", "6"),
    OccupationEnum271("0691", "6"),
    OccupationEnum272("0692", "6"),
    OccupationEnum273("0693", "6"),
    OccupationEnum274("0694", "6"),
    OccupationEnum275("0695", "6"),
    OccupationEnum276("0696", "6"),
    OccupationEnum277("0699", "3"),
    OccupationEnum278("0701", "6"),
    OccupationEnum279("0702", "6"),
    OccupationEnum280("0703", "6"),
    OccupationEnum281("0704", "6"),
    OccupationEnum282("0705", "6"),
    OccupationEnum283("0706", "6"),
    OccupationEnum284("0707", "6"),
    OccupationEnum285("0708", "6"),
    OccupationEnum286("0709", "6"),
    OccupationEnum287("0710", "6"),
    OccupationEnum288("0711", "6"),
    OccupationEnum289("0712", "6"),
    OccupationEnum290("0713", "6"),
    OccupationEnum291("0714", "6"),
    OccupationEnum292("0715", "6"),
    OccupationEnum293("0716", "6"),
    OccupationEnum294("0717", "6"),
    OccupationEnum295("0718", "6"),
    OccupationEnum296("0719", "6"),
    OccupationEnum297("0720", "6"),
    OccupationEnum298("0721", "6"),
    OccupationEnum299("0722", "6"),
    OccupationEnum300("0723", "6"),
    OccupationEnum301("0724", "6"),
    OccupationEnum302("0725", "6"),
    OccupationEnum303("0726", "6"),
    OccupationEnum304("0727", "6"),
    OccupationEnum305("0728", "6"),
    OccupationEnum306("0729", "6"),
    OccupationEnum307("0730", "6"),
    OccupationEnum308("0731", "6"),
    OccupationEnum309("0732", "6"),
    OccupationEnum310("0733", "6"),
    OccupationEnum311("0734", "6"),
    OccupationEnum312("0735", "6"),
    OccupationEnum313("0736", "6"),
    OccupationEnum314("0737", "6"),
    OccupationEnum315("0738", "6"),
    OccupationEnum316("0739", "6"),
    OccupationEnum317("0740", "6"),
    OccupationEnum318("0741", "6"),
    OccupationEnum319("0742", "6"),
    OccupationEnum320("0743", "6"),
    OccupationEnum321("0744", "6"),
    OccupationEnum322("0745", "6"),
    OccupationEnum323("0746", "6"),
    OccupationEnum324("0747", "6"),
    OccupationEnum325("0748", "6"),
    OccupationEnum326("0749", "6"),
    OccupationEnum327("0750", "6"),
    OccupationEnum328("0751", "6"),
    OccupationEnum329("0799", "6"),
    OccupationEnum330("0801", "6"),
    OccupationEnum331("0899", "6"),
    OccupationEnum332("0901", "6"),
    OccupationEnum333("0902", "6"),
    OccupationEnum334("0903", "6"),
    OccupationEnum335("0904", "6"),
    OccupationEnum336("0905", "6"),
    OccupationEnum337("0906", "6"),
    OccupationEnum338("0907", "6"),
    OccupationEnum339("0908", "6"),
    OccupationEnum340("0909", "6"),
    OccupationEnum341("0910", "6"),
    OccupationEnum342("0911", "6"),
    OccupationEnum343("0912", "6"),
    OccupationEnum344("0913", "6"),
    OccupationEnum345("0914", "6"),
    OccupationEnum346("0915", "6"),
    OccupationEnum347("0916", "6"),
    OccupationEnum348("0917", "6"),
    OccupationEnum349("0918", "6"),
    OccupationEnum350("0919", "6"),
    OccupationEnum351("0920", "6"),
    OccupationEnum352("0921", "6"),
    OccupationEnum353("0922", "6"),
    OccupationEnum354("0923", "6"),
    OccupationEnum355("0924", "6"),
    OccupationEnum356("0925", "6"),
    OccupationEnum357("0926", "6"),
    OccupationEnum358("0927", "6"),
    OccupationEnum359("0928", "6"),
    OccupationEnum360("0929", "6"),
    OccupationEnum361("0930", "6"),
    OccupationEnum362("0931", "6"),
    OccupationEnum363("0932", "6"),
    OccupationEnum364("0933", "6"),
    OccupationEnum365("0934", "6"),
    OccupationEnum366("0935", "6"),
    OccupationEnum367("0936", "6"),
    OccupationEnum368("0937", "6"),
    OccupationEnum369("0938", "6"),
    OccupationEnum370("0939", "6"),
    OccupationEnum371("0940", "6"),
    OccupationEnum372("0941", "6"),
    OccupationEnum373("0942", "6"),
    OccupationEnum374("0943", "6"),
    OccupationEnum375("0944", "6"),
    OccupationEnum376("0945", "6"),
    OccupationEnum377("0946", "6"),
    OccupationEnum378("0947", "6"),
    OccupationEnum379("0948", "6"),
    OccupationEnum380("0949", "6"),
    OccupationEnum381("0950", "6"),
    OccupationEnum382("0951", "6"),
    OccupationEnum383("0952", "6"),
    OccupationEnum384("0953", "6"),
    OccupationEnum385("0954", "6"),
    OccupationEnum386("0955", "6"),
    OccupationEnum387("0956", "6"),
    OccupationEnum388("0999", "6"),
    OccupationEnum389("1001", "6"),
    OccupationEnum390("1002", "6"),
    OccupationEnum391("1003", "6"),
    OccupationEnum392("1004", "6"),
    OccupationEnum393("1005", "6"),
    OccupationEnum394("1006", "6"),
    OccupationEnum395("1007", "6"),
    OccupationEnum396("1008", "6"),
    OccupationEnum397("1009", "6"),
    OccupationEnum398("1010", "6"),
    OccupationEnum399("1011", "6"),
    OccupationEnum400("1012", "6"),
    OccupationEnum401("1013", "6"),
    OccupationEnum402("1014", "6"),
    OccupationEnum403("1015", "6"),
    OccupationEnum404("1016", "6"),
    OccupationEnum405("1017", "6"),
    OccupationEnum406("1018", "6"),
    OccupationEnum407("1019", "6"),
    OccupationEnum408("1020", "6"),
    OccupationEnum409("1021", "6"),
    OccupationEnum410("1022", "6"),
    OccupationEnum411("1023", "6"),
    OccupationEnum412("1024", "6"),
    OccupationEnum413("1025", "6"),
    OccupationEnum414("1026", "6"),
    OccupationEnum415("1027", "6"),
    OccupationEnum416("1028", "6"),
    OccupationEnum417("1029", "6"),
    OccupationEnum418("1030", "6"),
    OccupationEnum419("1031", "6"),
    OccupationEnum420("1032", "6"),
    OccupationEnum421("1033", "6"),
    OccupationEnum422("1034", "6"),
    OccupationEnum423("1035", "6"),
    OccupationEnum424("1036", "6"),
    OccupationEnum425("1037", "6"),
    OccupationEnum426("1038", "6"),
    OccupationEnum427("1039", "6"),
    OccupationEnum428("1040", "6"),
    OccupationEnum429("1041", "6"),
    OccupationEnum430("1099", "6"),
    OccupationEnum431("1101", "6"),
    OccupationEnum432("1102", "6"),
    OccupationEnum433("1199", "6"),
    OccupationEnum434("1299", "5"),
    OccupationEnum435("1301", "6"),
    OccupationEnum436("1302", "6"),
    OccupationEnum437("1303", "6"),
    OccupationEnum438("1304", "6"),
    OccupationEnum439("1305", "6"),
    OccupationEnum440("1306", "6"),
    OccupationEnum441("1307", "6"),
    OccupationEnum442("1308", "6"),
    OccupationEnum443("1309", "6"),
    OccupationEnum444("1310", "6"),
    OccupationEnum445("1399", "6"),
    OccupationEnum446("1401", "4"),
    OccupationEnum447("1402", "4"),
    OccupationEnum448("1403", "4"),
    OccupationEnum449("1404", "4"),
    OccupationEnum450("1405", "4"),
    OccupationEnum451("1406", "4"),
    OccupationEnum452("1407", "4"),
    OccupationEnum453("1408", "4"),
    OccupationEnum454("1409", "4"),
    OccupationEnum455("1410", "4"),
    OccupationEnum456("1411", "4"),
    OccupationEnum457("1412", "4"),
    OccupationEnum458("1413", "4"),
    OccupationEnum459("1414", "4"),
    OccupationEnum460("1415", "4"),
    OccupationEnum461("1416", "4"),
    OccupationEnum462("1417", "4"),
    OccupationEnum463("1418", "4"),
    OccupationEnum464("1419", "4"),
    OccupationEnum465("1420", "4"),
    OccupationEnum466("1421", "4"),
    OccupationEnum467("1422", "4"),
    OccupationEnum468("1499", "4"),
    OccupationEnum469("1601", "6"),
    OccupationEnum470("1602", "6"),
    OccupationEnum471("1603", "6"),
    OccupationEnum472("1604", "6"),
    OccupationEnum473("1605", "6"),
    OccupationEnum474("1606", "6"),
    OccupationEnum475("1607", "6"),
    OccupationEnum476("1608", "6"),
    OccupationEnum477("1609", "6"),
    OccupationEnum478("1610", "6"),
    OccupationEnum479("1611", "6"),
    OccupationEnum480("1612", "6"),
    OccupationEnum481("1613", "6"),
    OccupationEnum482("1614", "6"),
    OccupationEnum483("1615", "6"),
    OccupationEnum484("1616", "6"),
    OccupationEnum485("1617", "6"),
    OccupationEnum486("1618", "6"),
    OccupationEnum487("1619", "6"),
    OccupationEnum488("1620", "6"),
    OccupationEnum489("1621", "6"),
    OccupationEnum490("1622", "6"),
    OccupationEnum491("1623", "6"),
    OccupationEnum492("1624", "6"),
    OccupationEnum493("1625", "6"),
    OccupationEnum494("1626", "6"),
    OccupationEnum495("1699", "6"),
    OccupationEnum496("1701", "6"),
    OccupationEnum497("1702", "6"),
    OccupationEnum498("1703", "6"),
    OccupationEnum499("1704", "6"),
    OccupationEnum500("1705", "6"),
    OccupationEnum501("1706", "6"),
    OccupationEnum502("1707", "6"),
    OccupationEnum503("1708", "6"),
    OccupationEnum504("1799", "6"),
    OccupationEnum505("1801", "6"),
    OccupationEnum506("1802", "6"),
    OccupationEnum507("1803", "6"),
    OccupationEnum508("1804", "6"),
    OccupationEnum509("1805", "6"),
    OccupationEnum510("1806", "6"),
    OccupationEnum511("1899", "6"),
    OccupationEnum512("1901", "6"),
    OccupationEnum513("1902", "6"),
    OccupationEnum514("1999", "6"),
    OccupationEnum515("2199", "6"),
    OccupationEnum516("2299", "6"),
    OccupationEnum517("2301", "6"),
    OccupationEnum518("2302", "6"),
    OccupationEnum519("2303", "6"),
    OccupationEnum520("2399", "6"),
    OccupationEnum521("2499", "6"),
    OccupationEnum522("2601", "6"),
    OccupationEnum523("2602", "6"),
    OccupationEnum524("2699", "6"),
    OccupationEnum525("2799", "6"),
    OccupationEnum526("2899", "6"),
    OccupationEnum527("2999", "6");

    public String occupationCode;
    public String grouppingCode;

    OccupationEnum(String occupationCode, String grouppingCode) {
        this.occupationCode = occupationCode;
        this.grouppingCode = grouppingCode;
    }

    public static OccupationEnum getOccupationCode(String occupationCode) {
        OccupationEnum[] enums = values();
        OccupationEnum[] codenums = enums;
        int var3 = enums.length;
        for (int i = 0; i < var3; ++i) {
            OccupationEnum e = codenums[i];
            if (StringUtils.equals(e.occupationCode, occupationCode)) {
                return e;
            }
        }
        return null;
    }

    public static OccupationEnum getGrouppingCode(String grouppingCode) {
        OccupationEnum[] enums = values();
        OccupationEnum[] codenums = enums;
        int var3 = enums.length;
        for (int i = 0; i < var3; ++i) {
            OccupationEnum e = codenums[i];
            if (StringUtils.equals(e.grouppingCode, grouppingCode)) {
                return e;
            }
        }
        return null;
    }


    public static void main(String[] args) {
        OccupationEnum occupationEnum =   OccupationEnum.getOccupationCode("2899");
        System.out.print(occupationEnum.grouppingCode);
    }
}