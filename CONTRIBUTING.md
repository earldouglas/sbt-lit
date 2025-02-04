[![Build status](https://github.com/earldouglas/sbt-lit/workflows/build/badge.svg)](https://github.com/earldouglas/sbt-lit/actions)
[![Latest version](https://img.shields.io/github/tag/earldouglas/sbt-lit.svg)](https://index.scala-lang.org/earldouglas/sbt-lit)

# Contributing

## Publishing

This project uses [sbt-sonatype](https://github.com/xerial/sbt-sonatype)
to automate most of the publishing process.

```
$ export OLD_VERSION=0.0.5
$ export NEW_VERSION=0.0.6
```

Update the documentation:

```
$ git checkout -b v$NEW_VERSION
$ sed -i "s/$OLD_VERSION/$NEW_VERSION/g" README.md
$ git add README.md
$ git commit -m "sbt-lit: $OLD_VERSION -> $NEW_VERSION"
$ git push origin v$NEW_VERSION
```

Publish:

```
$ nix-shell
$ sbt
> set ThisBuild / version := sys.env("NEW_VERSION")
> +publishSigned
> sonatypeBundleRelease
```

Tag:

```
$ git tag -m "sbt-lit: $NEW_VERSION" $NEW_VERSION
$ git push --tags origin
```
