package com.mygdx.assets;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SynchronousAssetLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;

public class ContextLoader
		extends SynchronousAssetLoader<ApplicationContext, AssetLoaderParameters<ApplicationContext>> {

	ApplicationContext context;

	public ContextLoader(FileHandleResolver resolver) {
		super(resolver);
	}

	@Override
	public ApplicationContext load(AssetManager assetManager, String fileName, FileHandle file,
			AssetLoaderParameters<ApplicationContext> parameter) {
		context = new ClassPathXmlApplicationContext(fileName);
		return context;
	}

	@Override
	public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file,
			AssetLoaderParameters<ApplicationContext> parameter) {
		return null;
	}

}
