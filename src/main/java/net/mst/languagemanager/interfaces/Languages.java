package net.mst.languagemanager.interfaces;

public enum Languages {

    GERMAN ("DE"),
    ENGLISH ("EN"),
    SPANISH ("SN"),
    LATIN ("LAT");

    private final String local;

	private Languages(final String local) {
        this.local = local;
	}

}
