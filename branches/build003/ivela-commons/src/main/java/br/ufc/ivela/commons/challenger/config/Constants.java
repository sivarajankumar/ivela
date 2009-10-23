package br.ufc.ivela.commons.challenger.config;

/**
 * Aims to provide a genetic and global
 * inteface with all configuration properties for entire application
 * @author Jose Damico - damico@dcon.com.br (16Sep2008)
 *
 */

public interface Constants {
    
    public static final int CHALLENGE_TYPE = 1;
    public static final int SCORE_TYPE = 2;
    
	public static final int XML_INTERNALS_CHALLENGEREF = 1;
	public static final String SUBTYPE_QUESTIONSET = "questionset";
	public static final String TAG_HEADER = "header";
	public static final String TAG_PAYLOAD = "payload";
	public static final String TAG_CHALLENGEREF = "challengeref";
	public static final String TAG_CHALLENGEDESC = "challengedesc";
	public static final String DOCTYPE = "doctype";
	public static final String DOCSUBTYPE = "docsubtype";
	public static final String CHALLENGEDESC_STYLE = "style";
	public static final String CHALLENGEDESC_NAME = "name";
	public static final String CHALLENGEDESC_TOTALVALUE = "totalvalue";
	public static final String CHALLENGEDESC_MAXTRIES = "maxtries";
	public static final String CHALLENGEDESC_SHORTDESC = "shortdesc";
	public static final String CHALLENGEREF_VALUE = "value";
	public static final String SEPARATE_LOG_FILE = "/tmp/ic.log";
	public static final boolean IS_SEPARATE_LOG = false;
	public static final String SEVERE_LOGLEVEL = " S ";
	public static final String NORMAL_LOGLEVEL = " N ";
	public static final int XML_PLUGIN_QUESTIONSET = 2;
	public static final String TAG_QUESTION = "question";
        
	public static final String QUESTION_NAME = "name";
	public static final String QUESTION_REFERENCE = "reference";
	public static final String QUESTION_VALUE = "value";
	public static final String TAG_QUESTIONOPTION = "option";
	public static final String OPTION_NAME = "name";
	public static final String OPTION_DESC = "desc";
	public static final String OPTION_CORRECT = "correct";
	public static final String TAG_QUESTIONDESC = "description";
	public static final String TAG_IVELA = "ivela";

	/* Leonardo Moreira */
        // Constantes para o uso da funcionalidade ContentPackage
        public static final String UPLOAD_DIRECTORY = "/uploads";
        public static final int CONTENTPACKAGE = 3;
        public static final String CONTENTPACKAGE_DESCRIPTORNAME = "contentpkg.ivela.xml";
        public static final String TAG_CONTENTPACKAGE = "contentpackage";
        public static final String CONTENTPACKAGE_GRADE = "grade";
        public static final String CONTENTPACKAGE_COURSE = "course";
        public static final String CONTENTPACKAGE_DISCIPLINE = "discipline";
        public static final String CONTENTPACKAGE_UNIT = "unit";
        public static final String CONTENTPACKAGE_UNITCONTENT = "unitcontent";
        public static final String TAG_FILESYSTEM = "filesystem";
        public static final String FILESYSTEM_VALUE = "value";
        /* --- */

}
