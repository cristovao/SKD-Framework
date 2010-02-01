/**
 * 
 */
package org.ybacoby.skdframework;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URI;
import java.util.ResourceBundle;

/**
 * @author cristovao
 * 
 */
public abstract class I18nAllSystem {

	private transient ResourceBundle resource;

	private transient File file;

	public I18nAllSystem() {
		this.resource = ResourceBundle.getBundle("I18nResource");
	}

	protected boolean containsOnlyNumbers(String str) {

		// It can't contain only numbers if it's null or empty...
		if (str == null || str.length() == 0)
			return false;

		for (int i = 0; i < str.length(); i++) {

			// If we find a non-digit character we return false.
			if (!Character.isDigit(str.charAt(i)))
				return false;
		}

		return true;
	}

	/**
	 * Retorna o nome de um dado componente traduzido
	 * 
	 * @param name
	 *            Nome do componente
	 * @return Sua tradução internacionalizada
	 */
	protected String getResource(String name) {
		return resource.getString(name);
	}

	protected void deleteFile(String nameFile) {
		file = new File(nameFile);

		if (file.isFile()) {
			file.delete();
			return;
		}

		file = null;
	}

	protected void setFile(String file) {
		this.file = new File(file);
	}

	/**
	 * @return
	 * @see java.io.File#canRead()
	 */
	protected boolean canRead() {
		return file.canRead();
	}

	/**
	 * @return
	 * @see java.io.File#canWrite()
	 */
	protected boolean canWrite() {
		return file.canWrite();
	}

	/**
	 * @param pathname
	 * @return
	 * @see java.io.File#compareTo(java.io.File)
	 */
	protected int compareTo(File pathname) {
		return file.compareTo(pathname);
	}

	/**
	 * @return
	 * @throws IOException
	 * @see java.io.File#createNewFile()
	 */
	protected boolean createNewFile() throws IOException {
		return file.createNewFile();
	}

	/**
	 * @return
	 * @see java.io.File#delete()
	 */
	protected boolean delete() {
		return file.delete();
	}

	/**
	 * 
	 * @see java.io.File#deleteOnExit()
	 */
	protected void deleteOnExit() {
		file.deleteOnExit();
	}

	/**
	 * @return
	 * @see java.io.File#exists()
	 */
	protected boolean exists() {
		return file.exists();
	}

	/**
	 * @return
	 * @see java.io.File#getAbsoluteFile()
	 */
	protected File getAbsoluteFile() {
		return file.getAbsoluteFile();
	}

	/**
	 * @return
	 * @see java.io.File#getAbsolutePath()
	 */
	protected String getAbsolutePath() {
		return file.getAbsolutePath();
	}

	/**
	 * @return
	 * @throws IOException
	 * @see java.io.File#getCanonicalFile()
	 */
	protected File getCanonicalFile() throws IOException {
		return file.getCanonicalFile();
	}

	/**
	 * @return
	 * @throws IOException
	 * @see java.io.File#getCanonicalPath()
	 */
	protected String getCanonicalPath() throws IOException {
		return file.getCanonicalPath();
	}

	/**
	 * @return
	 * @see java.io.File#getFreeSpace()
	 */
	protected long getFreeSpace() {
		return file.getFreeSpace();
	}

	/**
	 * @return
	 * @see java.io.File#getName()
	 */
	protected String getName() {
		return file.getName();
	}

	/**
	 * @return
	 * @see java.io.File#getParent()
	 */
	protected String getParent() {
		return file.getParent();
	}

	/**
	 * @return
	 * @see java.io.File#getParentFile()
	 */
	protected File getParentFile() {
		return file.getParentFile();
	}

	/**
	 * @return
	 * @see java.io.File#getPath()
	 */
	protected String getPath() {
		return file.getPath();
	}

	/**
	 * @return
	 * @see java.io.File#getTotalSpace()
	 */
	protected long getTotalSpace() {
		return file.getTotalSpace();
	}

	/**
	 * @return
	 * @see java.io.File#getUsableSpace()
	 */
	protected long getUsableSpace() {
		return file.getUsableSpace();
	}

	/**
	 * @return
	 * @see java.io.File#isAbsolute()
	 */
	protected boolean isAbsolute() {
		return file.isAbsolute();
	}

	/**
	 * @return
	 * @see java.io.File#isDirectory()
	 */
	protected boolean isDirectory() {
		return file.isDirectory();
	}

	/**
	 * @return
	 * @see java.io.File#isFile()
	 */
	protected boolean isFile() {
		return file.isFile();
	}

	/**
	 * @return
	 * @see java.io.File#isHidden()
	 */
	protected boolean isHidden() {
		return file.isHidden();
	}

	/**
	 * @return
	 * @see java.io.File#lastModified()
	 */
	protected long lastModified() {
		return file.lastModified();
	}

	/**
	 * @return
	 * @see java.io.File#length()
	 */
	protected long length() {
		return file.length();
	}

	/**
	 * @return
	 * @see java.io.File#list()
	 */
	protected String[] list() {
		return file.list();
	}

	/**
	 * @param filter
	 * @return
	 * @see java.io.File#list(java.io.FilenameFilter)
	 */
	protected String[] list(FilenameFilter filter) {
		return file.list(filter);
	}

	/**
	 * @return
	 * @see java.io.File#listFiles()
	 */
	protected File[] listFiles() {
		return file.listFiles();
	}

	/**
	 * @param filter
	 * @return
	 * @see java.io.File#listFiles(java.io.FileFilter)
	 */
	protected File[] listFiles(FileFilter filter) {
		return file.listFiles(filter);
	}

	/**
	 * @param filter
	 * @return
	 * @see java.io.File#listFiles(java.io.FilenameFilter)
	 */
	protected File[] listFiles(FilenameFilter filter) {
		return file.listFiles(filter);
	}

	/**
	 * @return
	 * @see java.io.File#mkdir()
	 */
	protected boolean mkdir() {
		return file.mkdir();
	}

	/**
	 * @return
	 * @see java.io.File#mkdirs()
	 */
	protected boolean mkdirs() {
		return file.mkdirs();
	}

	/**
	 * @param dest
	 * @return
	 * @see java.io.File#renameTo(java.io.File)
	 */
	protected boolean renameTo(File dest) {
		return file.renameTo(dest);
	}

	/**
	 * @param executable
	 * @param ownerOnly
	 * @return
	 * @see java.io.File#setExecutable(boolean, boolean)
	 */
	protected boolean setExecutable(boolean executable, boolean ownerOnly) {
		return file.setExecutable(executable, ownerOnly);
	}

	/**
	 * @param executable
	 * @return
	 * @see java.io.File#setExecutable(boolean)
	 */
	protected boolean setExecutable(boolean executable) {
		return file.setExecutable(executable);
	}

	/**
	 * @param time
	 * @return
	 * @see java.io.File#setLastModified(long)
	 */
	protected boolean setLastModified(long time) {
		return file.setLastModified(time);
	}

	/**
	 * @param readable
	 * @param ownerOnly
	 * @return
	 * @see java.io.File#setReadable(boolean, boolean)
	 */
	protected boolean setReadable(boolean readable, boolean ownerOnly) {
		return file.setReadable(readable, ownerOnly);
	}

	/**
	 * @param readable
	 * @return
	 * @see java.io.File#setReadable(boolean)
	 */
	protected boolean setReadable(boolean readable) {
		return file.setReadable(readable);
	}

	/**
	 * @return
	 * @see java.io.File#setReadOnly()
	 */
	protected boolean setReadOnly() {
		return file.setReadOnly();
	}

	/**
	 * @param writable
	 * @param ownerOnly
	 * @return
	 * @see java.io.File#setWritable(boolean, boolean)
	 */
	protected boolean setWritable(boolean writable, boolean ownerOnly) {
		return file.setWritable(writable, ownerOnly);
	}

	/**
	 * @param writable
	 * @return
	 * @see java.io.File#setWritable(boolean)
	 */
	protected boolean setWritable(boolean writable) {
		return file.setWritable(writable);
	}

	/**
	 * @return
	 * @see java.io.File#toURI()
	 */
	protected URI toURI() {
		return file.toURI();
	}
}
